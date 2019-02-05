<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="UpdateStatus.aspx.cs" Inherits="Tabeeb.UpdateStatus" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="jumbotron vertical-center">
        <h1>Update Status</h1>
    </div>
    <div class="row margin-bottom well" style="margin-bottom: 20px;">
        <div class="row margin-bottom">
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>User Name : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:Label runat="server" ID="UserName" >-</asp:Label>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Doctor Name : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:Label runat="server" ID="DocName" >-</asp:Label>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Appointment Time : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:Textbox runat="server" type="datetime-local" ID="AppTime" value="2019-02-07T19:30"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row margin-bottom">
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Status : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:DropDownList runat="server" ID="status">
                            <asp:ListItem Text="Pending" Value="0" />
                            <asp:ListItem Text="Approved" Value="1" />
                        </asp:DropDownList>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <asp:Button class="btn btn-primary" runat="server" ID="update" Text="Update Status" OnClick="update_Click" />
            </div>
        </div>

    </div>
</asp:Content>
