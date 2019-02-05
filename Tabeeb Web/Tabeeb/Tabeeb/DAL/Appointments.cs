using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Tabeeb.DAL
{
    public class Appointments
    {
        public string ID { get; set; }
        public string appTime { get; set; }
        public string comment { get; set; }
        public string docID { get; set; }
        public string docName { get; set; }
        public string status { get; set; }
        public string userID { get; set; }
        public string userName { get; set; }
    }
}