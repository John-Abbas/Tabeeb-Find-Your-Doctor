<%@ Page Title="Hospitals" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Hospitals.aspx.cs" Inherits="Tabeeb.Hospitals" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
  <h1>Hospitals</h1>
  <div>
  <p>
    <label >Name : </label>
    <asp:Textbox runat="server" id="name"/>
  </p>
  <p>
    <label>Location : </label>
    <asp:Textbox runat="server" id="location"/>
  </p>
  <p>
    <label>Latitude : </label>
    <asp:Textbox runat="server" id="latitude"/>
  </p>
  <p>
    <label>Longitude : </label>
    <asp:Textbox runat="server" id="longitude"/>
  </p>

  <p>
    <label>Verification : </label>
    <asp:DropDownList runat="server" id="verification">
        <asp:ListItem Text="Verified" Value="0"/>
        <asp:ListItem Text="Not-Verified" Value="1"/>
    </asp:DropDownList>
  </p>
  </div>

    <asp:Button runat="server" id="add" Text="Add Hospital" OnClick="AddBtn_Click" />

    <asp:GridView ID="gvHospitals" CssClass="table table-striped" AutoGenerateColumns="false" DataKeyNames="ID" ShowHeaderWhenEmpty="true" runat="server" OnRowCommand="gvHospital_RowCommand">
        <Columns>
            <asp:BoundField DataField="Name" HeaderText="Hospital" SortExpression="Name" />
            <asp:BoundField DataField="Location" HeaderText="Location" SortExpression="Location" />
            <asp:BoundField DataField="Verification" HeaderText="Verified" SortExpression="Verification" />
            
            
        </Columns>
    </asp:GridView>
</asp:Content>