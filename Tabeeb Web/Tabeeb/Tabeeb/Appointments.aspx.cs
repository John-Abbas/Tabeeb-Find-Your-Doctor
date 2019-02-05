using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Tabeeb
{
    public partial class Appointments : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void gvAppointments_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            int rowIndex = int.Parse(e.CommandArgument.ToString());
            GridViewRow row = gvAppointments.Rows[rowIndex];
            string ID = (string)gvAppointments.DataKeys[rowIndex].Value;
            string userName = row.Cells[0].Text;
            string docName = row.Cells[1].Text;
            string statusStr = status.SelectedItem.ToString();

            if (e.CommandName == "editRec")
            {
                Response.Redirect("UpdateStatus.aspx?appointmentID=" + ID + "&&userName=" + userName + "&&docName=" + docName + "&&status=" + statusStr);
            }
        }

        protected void search_Click(object sender, EventArgs e)
        {
            retrieveRecords(status.SelectedItem.ToString());
        }

        private void retrieveRecords(String status)
        {
            var request = (HttpWebRequest)WebRequest.Create("https://tabeeb-17d27.firebaseio.com/Appointments/.json");
            var userRequest = (HttpWebRequest)WebRequest.Create("https://tabeeb-17d27.firebaseio.com/UserDetails/.json");
            request.ContentType = "application/json";
            userRequest.ContentType = "application/json";
            var response = request.GetResponse() as HttpWebResponse;
            var userResponse = userRequest.GetResponse() as HttpWebResponse;

            using (Stream str = response.GetResponseStream())
            {
                StreamReader reader = new StreamReader(str, Encoding.UTF8);
                string data = reader.ReadToEnd();
                if (!data.Equals("null"))
                {
                    using (Stream userStr = userResponse.GetResponseStream())
                    {
                        StreamReader userReader = new StreamReader(userStr, Encoding.UTF8);
                        string userData = userReader.ReadToEnd();

                        List<DAL.Appointments> appointments = ParseAppointments(data,userData, status);
                        BindFields(appointments);
                    }
                    
                }
                    
            }
        }

        public List<DAL.Appointments> ParseAppointments(string json, string userJson,string status)
        {
            List<DAL.Appointments> appointments = new List<DAL.Appointments>();
            List<DAL.UserDetails> users = ParseUsers(userJson);

            JObject jObject = JObject.Parse(json);
            var jUser = jObject.Children().ToList();

            foreach (JToken token in jUser)
            {
                var appointment = token.First();
                if (((string)appointment["status"]).Equals(status))
                {
                    string userName = users.Find(x => x.ID == (string)appointment["userID"]).name;
                    appointments.Add(new DAL.Appointments()
                    {
                        ID = (string)token.First().Path,
                        appTime = (string)appointment["appTime"],
                        comment = (string)appointment["comment"],
                        docID = (string)appointment["docID"],
                        docName = (string)appointment["docName"],
                        status = (string)appointment["status"],
                        userID = (string)appointment["userID"],
                        userName = userName

                    });
                }
            }

            return appointments;
        }

        private List<DAL.UserDetails> ParseUsers(string userJson)
        {
            List<DAL.UserDetails> users = new List<DAL.UserDetails>();

            JObject jObject = JObject.Parse(userJson);
            var jUser = jObject.Children().ToList();

            foreach (JToken token in jUser)
            {
                var user = token.First();

                users.Add(new DAL.UserDetails()
                        {
                            ID = (string)token.First().Path,
                            age = (int)user["age"],
                            email = (string)user["email"],
                            gender = (string)user["gender"],
                            name = (string)user["name"],
                            phoneNumber = (string)user["phoneNum"],
                            userType = (string)user["userType"]
                        });
            }
            return users;
        }

        private void BindFields(List<DAL.Appointments> appointments)
        {
            gvAppointments.DataSource = appointments;
            gvAppointments.DataBind();
        }
    }
}