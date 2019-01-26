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
    public partial class Hospitals : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            retrieveRecords();
        }

        private void retrieveRecords()
        {
            var request = (HttpWebRequest)WebRequest.Create("https://tabeeb-17d27.firebaseio.com/Hospitals/.json");
            request.ContentType = "application/json";
            var response = request.GetResponse() as HttpWebResponse;
            using (Stream str = response.GetResponseStream())
            {
                StreamReader reader = new StreamReader(str, Encoding.UTF8);
                string data = reader.ReadToEnd();
                if (!data.Equals("null"))
                    BindFields(ParseHospitals(data));
            }
        }

        public List<DAL.Hospitals> ParseHospitals(string json)
        {
            List<DAL.Hospitals> hopitals = new List<DAL.Hospitals>();
            JObject jObject = JObject.Parse(json);
            var jUser = jObject.Children().ToList();

            foreach (JToken token in jUser)
            {
                var hospital = token.First();
                hopitals.Add(new DAL.Hospitals()
                {
                    ID = (string)token.First().Path,
                    Location = (string)hospital["Location"],
                    Name = (string)hospital["Name"],
                    Verification = (int)hospital["Verification"],
                    Latitude = (string)hospital["Latitude"],
                    Longitude = (string)hospital["Longitude"]

                });
            }

            return hopitals;
        }

        private void BindFields(List<DAL.Hospitals> hopitals)
        {
            gvHospitals.DataSource = hopitals;
            gvHospitals.DataBind();
        }

        protected void AddBtn_Click(object sender, EventArgs e)
        {
            var json = Newtonsoft.Json.JsonConvert.SerializeObject(new
            {
                Name = name.Text,
                Location = location.Text,
                Latitude = latitude.Text,
                Longitude = longitude.Text,
                Verification = Convert.ToInt32(verification.SelectedValue)
            });

            var request = WebRequest.CreateHttp("https://tabeeb-17d27.firebaseio.com/Hospitals/.json");
            request.Method = "POST";
            request.ContentType = "application/json";
            var buffer = Encoding.UTF8.GetBytes(json);
            request.ContentLength = buffer.Length;
            request.GetRequestStream().Write(buffer, 0, buffer.Length);
            var response = request.GetResponse();
            json = (new StreamReader(response.GetResponseStream())).ReadToEnd();

            retrieveRecords();
        }

        protected void gvHospital_RowCommand(object sender, GridViewCommandEventArgs e)
        { }
    }
}