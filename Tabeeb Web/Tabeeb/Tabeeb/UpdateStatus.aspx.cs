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
    public partial class UpdateStatus : System.Web.UI.Page
    {
        public static string appId;

        protected void Page_Load(object sender, EventArgs e)
        {
            if(!IsPostBack)
            {
            AppTime.Text = DateTime.Now.ToString("yyyy-MM-ddTHH:mm");
            if (Request.QueryString["appointmentID"] != null)
            {
                appId = Request.QueryString["appointmentID"];
                UserName.Text = Request.QueryString["userName"];
                DocName.Text = Request.QueryString["docName"];
                status.Text = Request.QueryString["status"];
            }
            }


        }

        protected void update_Click(object sender, EventArgs e)
        {
            updateRecord(retrieveRecord());
        }

        
        private DAL.Appointments retrieveRecord()
        {
            DAL.Appointments appointment = new DAL.Appointments();

            string httpReq = "https://tabeeb-17d27.firebaseio.com/Appointments/" + appId + "/.json";
            var request = (HttpWebRequest)WebRequest.Create(httpReq);
            request.ContentType = "application/json";
            var response = request.GetResponse() as HttpWebResponse;

            using (Stream str = response.GetResponseStream())
            {
                StreamReader reader = new StreamReader(str, Encoding.UTF8);
                string data = reader.ReadToEnd();
                if (!data.Equals("null"))
                {

                    appointment = ParseAppointment(data);

                }

            }

            return appointment;
        }

        public DAL.Appointments ParseAppointment(string json)
        {
            DAL.Appointments appointment =  null;
            JObject jObject = JObject.Parse(json);
            if(jObject != null)
            {
                appointment = new DAL.Appointments() { 
                    ID = appId,
                    appTime = (string)jObject["appTime"],
                    comment = (string)jObject["comment"],
                    docID = (string)jObject["docID"],
                    docName = (string)jObject["docName"],
                    status = (string)jObject["status"],
                    userID = (string)jObject["userID"],
                    userName = (string)jObject["userName"]
                };
            }
            return appointment;
        }

        private void updateRecord(DAL.Appointments appointment)
        {
            var json = Newtonsoft.Json.JsonConvert.SerializeObject(new
            {
                appTime = AppTime.Text,
                comment = appointment.comment,
                docID = appointment.docID,
                docName = appointment.docName,
                status = status.SelectedItem.ToString(),
                userID = appointment.userID
            });

            string httpReq = "https://tabeeb-17d27.firebaseio.com/Appointments/" + appId + "/.json";
            var request = WebRequest.CreateHttp(httpReq);
            request.Method = "PATCH";
            request.ContentType = "application/json";
            var buffer = Encoding.UTF8.GetBytes(json);
            request.ContentLength = buffer.Length;
            request.GetRequestStream().Write(buffer, 0, buffer.Length);
            var response = request.GetResponse();
            json = (new StreamReader(response.GetResponseStream())).ReadToEnd();
        }
    }
}