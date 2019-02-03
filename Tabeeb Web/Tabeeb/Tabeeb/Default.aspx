<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="Tabeeb._Default" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

    <div class="jumbotron vertical-center">
        <h1>Tabeeb - Find Your Doctor</h1>
    </div>

    <div class="row">
        <div class="col-xs-4 vert-align " style="padding: 50px;">
            <a href="Add Doctor">
                <div class="panel panel-default" style="padding: 50px;">

                    <img src="/photos/add-doctor.jpg" class="img-rounded image-size" />

                </div>
            </a>
        </div>
        <div class="col-xs-4 vert-align " style="padding: 50px;">
            <a href="Hospitals">
                <div class="panel panel-default" style="padding: 50px;">

                    <img src="/photos/add-hospital.jpg" class="img-rounded image-size" />

                </div>
            </a>
        </div>
        <div class="col-xs-4 vert-align " style="padding: 50px;">
            <a href="Contact">
                <div class="panel panel-default" style="padding: 50px;">

                    <img src="/photos/contact-us.jpg" class="img-rounded image-size" />

                </div>
            </a>
        </div>
    </div>

</asp:Content>
