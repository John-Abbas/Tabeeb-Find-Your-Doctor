using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Net;
using System.Text;  // for class Encoding
using System.IO;
using Newtonsoft.Json.Linq;

namespace Tabeeb
{
    public partial class Add_Doctor : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            retrieveRecords();
        }

        private void retrieveRecords()
        {
            var request = (HttpWebRequest)WebRequest.Create("https://tabeeb-17d27.firebaseio.com/.json"); //"https://tabeeb-17d27.firebaseio.com/Doctors/.json");
            request.ContentType = "application/json";
            try { 
                
                var response = request.GetResponse() as HttpWebResponse;
            
                using (Stream str = response.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(str, Encoding.UTF8);
                    string data = reader.ReadToEnd();
                    BindFields(ParseDoctors(data));
                }
            }
            catch { }
            
            
        }

        public List<DAL.Doctors> ParseDoctors(string json)
        {
            List<DAL.Specialization> specs = ParseSpecialization(json);
            List<DAL.Hospitals> hosps = ParseHospitals(json);
            List<DAL.Doctors> docs = new List<DAL.Doctors>();
            JObject jObject = JObject.Parse(json);
            var Doctors = jObject["Doctors"];
            var jUser = Doctors.Children().ToList();


            foreach (JToken token in jUser)
            {
                var doc = token.First();
                docs.Add(new DAL.Doctors(){
                    ID = (string)token.First().Path,
                    Age = (int)doc["Age"],
                    Fee = (string)doc["Fee"],
                    Gender = (int)doc["Gender"],
                    Hours = (string)doc["Hours"],
                    Location = hosps.Find(x => x.ID == (string)doc["Location"]).Name,
                    Name = (string)doc["Name"],
                    Special = (int)doc["Special"],
                    specialName = (string)specs[(int)doc["Special"]].Special,
                    Verification = (int)doc["Verification"]

                });
            }

            return docs;
        }

        private List<DAL.Specialization> ParseSpecialization(string json) 
        {
            List<DAL.Specialization> specials = new List<DAL.Specialization>();
            JObject jObject = JObject.Parse(json);
            var Special = jObject["Specialization"];
            var jUser = Special.Children().ToList();

            foreach (JToken token in jUser)
            {
                specials.Add(new DAL.Specialization()
                {
                    ID = (string)token.Path,
                    Special = (string)token

                });
            }
            return specials;
        }

        private List<DAL.Hospitals> ParseHospitals(string json)
        {
            List<DAL.Hospitals> hosps = new List<DAL.Hospitals>();
            JObject jObject = JObject.Parse(json);
            var Special = jObject["Hospitals"];
            var jUser = Special.Children().ToList();

            foreach (JToken token in jUser)
            {
                var id = token.First().Path.Substring(10, token.First().Path.Length - 10);
                hosps.Add(new DAL.Hospitals()
                {
                    ID = id,
                    Location = (string)token.First()["Location"],
                    Name = (string)token.First()["Name"],
                    Verification = (int)token.First()["Verification"],
                    Latitude = (string)token.First()["Latitude"],
                    Longitude = (string)token.First()["Longitude"],
                    Contact = (string)token.First()["Contact"]
                });
            }

            return hosps;
        }

        private void BindFields(List<DAL.Doctors> docs)
        {
            gvStudents.DataSource = docs;
            gvStudents.DataBind();
        }

        protected void Unnamed1_Click(object sender, EventArgs e)
        {
            if (validateInputs())
                enterRecord();
        }

        private void enterRecord()
        {
            var json = Newtonsoft.Json.JsonConvert.SerializeObject(new
            {
                Name = name.Text,
                Special = Convert.ToInt32(special.SelectedValue),
                Location = location.Text,
                Fee = fee.Text.Equals("") ? "N/A" : fee.Text,
                Hours = hours.Text,
                Age = Convert.ToInt32(age.Text),
                Gender = Convert.ToInt32(gender.SelectedValue),
                Verification = Convert.ToInt32(verification.SelectedValue)
            });


            var request = WebRequest.CreateHttp("https://tabeeb-17d27.firebaseio.com/Doctors/.json");
            request.Method = "POST";
            request.ContentType = "application/json";
            var buffer = Encoding.UTF8.GetBytes(json);
            request.ContentLength = buffer.Length;
            request.GetRequestStream().Write(buffer, 0, buffer.Length);
            var response = request.GetResponse();
            json = (new StreamReader(response.GetResponseStream())).ReadToEnd();

            retrieveRecords();
        }

        private Boolean validateInputs()
        {
            if (name.Text.Equals(""))
                return false;
            if (location.Text.Equals(""))
                return false;
            if (fee.Text.Equals(""))
                return false;
            if (hours.Text.Equals(""))
                return false;
            if (age.Text.Equals(""))
                return false;

            return true;
        }

        protected void gvStudents_RowCommand(object sender, GridViewCommandEventArgs e)
        {

        }
    }
}