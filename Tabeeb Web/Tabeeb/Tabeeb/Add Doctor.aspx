<%@ Page Title="Add Doctor" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Add Doctor.aspx.cs" Inherits="Tabeeb.Add_Doctor" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

  <h1>Add Doctor</h1>
  <div class="inset">
  <p>
    <label >Name : </label>
    <asp:Textbox runat="server" id="name"/>
  </p>
  <p>
    <label >Specialization : </label>
    <asp:DropDownList runat="server" id="special">
        <asp:ListItem Text="Physician" Value="0"/>
        <asp:ListItem Text="Neurologist" Value="1"/>
        <asp:ListItem Text="Cardiologist" Value="2"/>
        <asp:ListItem Text="Dentist" Value="3"/>
        <asp:ListItem Text="Gynecologist" Value="4"/>
        <asp:ListItem Text="Hematologist" Value="5"/>
        <asp:ListItem Text="ENT Specialist" Value="6"/>
        <asp:ListItem Text="Trichologist" Value="7"/>
    </asp:DropDownList>
  </p>
  <p>
    <label>Location : </label>
    <asp:Textbox runat="server" id="location"/>
  </p>
  <p>
    <label>Fee : </label>
    <asp:Textbox runat="server" id="fee" TextMode="Number"/>
  </p>
  <p>
    <label>Hours : </label>
    <asp:Textbox runat="server" id="hours"/>
  </p>
  <p>
    <label>Age : </label>
    <asp:Textbox runat="server" id="age" TextMode="Number"/>
  </p>
  <p>
    <label>Gender : </label>
    <asp:DropDownList runat="server" id="gender">
        <asp:ListItem Text="Male" Value="0"/>
        <asp:ListItem Text="Female" Value="1"/>
    </asp:DropDownList>
  </p>

  <p>
    <label>Verification : </label>
    <asp:DropDownList runat="server" id="verification">
        <asp:ListItem Text="Verified" Value="0"/>
        <asp:ListItem Text="Un-Verified" Value="1"/>
    </asp:DropDownList>
  </p>
  </div>
      
  <asp:Button runat="server" id="submit" Text="Add Doctor" OnClick="Unnamed1_Click" />


</asp:Content>
