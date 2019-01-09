using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Net;
using System.Text;  // for class Encoding
using System.IO;

namespace Tabeeb
{
    public partial class Add_Doctor : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            //Button btn = (Button)FindControl("submit");
            //btn.Click += new EventHandler(btnLogin_Click);
        }

        protected void Unnamed1_Click(object sender, EventArgs e)
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
        }
    }
}