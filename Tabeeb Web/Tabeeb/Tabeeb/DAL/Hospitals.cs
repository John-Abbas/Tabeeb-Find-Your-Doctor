using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Tabeeb.DAL
{
    public class Hospitals
    {
        public string ID { get; set; }
        public string Name { get; set; }
        public string Location { get; set; }
        public string Latitude { get; set; }
        public string Longitude { get; set; }
        public int Verification { get; set; }

    }
}