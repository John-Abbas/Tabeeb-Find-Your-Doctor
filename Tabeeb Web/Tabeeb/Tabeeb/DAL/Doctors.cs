using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Newtonsoft.Json.Linq;

namespace Tabeeb.DAL
{
    public class Doctors
    {
        public string ID { get; set; }
        public int Age { get; set; }
        public string Fee { get; set; }
        public int Gender { get; set; }
        public string Hours { get; set; }
        public string Location { get; set; }
        public string Name { get; set; }
        public int Special { get; set; }
        public int Verification { get; set; }
    }
}