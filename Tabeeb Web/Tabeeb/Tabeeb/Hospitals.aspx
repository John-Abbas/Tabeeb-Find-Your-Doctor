<%@ Page Title="Hospitals" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Hospitals.aspx.cs" Inherits="Tabeeb.Hospitals" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="jumbotron vertical-center">
        <h1>Hospitals</h1>
    </div>
    <div class="row margin-bottom well" style="margin-bottom: 20px;">
        <div class="row margin-bottom">
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Name : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:TextBox runat="server" ID="name" type="text" CssClass="form-control width-standard" />
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Location : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:TextBox runat="server" ID="location" type="text" CssClass="form-control width-standard" />
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Latitude : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:TextBox runat="server" ID="latitude" type="text" CssClass="form-control width-standard" />
                    </div>
                </div>
            </div>
        </div>
        <div class="row margin-bottom">
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Longitude : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:TextBox runat="server" ID="longitude" type="text" CssClass="form-control width-standard" />
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Contact : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:TextBox runat="server" ID="Contact" type="number" CssClass="form-control width-standard" />
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Verification : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:DropDownList runat="server" ID="verification" CssClass="width-standard">
                            <asp:ListItem Text="Verified" Value="0" />
                            <asp:ListItem Text="Not-Verified" Value="1" />
                        </asp:DropDownList>
                    </div>
                </div>
            </div>
        </div>
        <div class="row margin-bottom">
            <div class="col-xs-4"></div>
            <div class="col-xs-4"></div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4"></div>
                    <div class="col-xs-4">
                        <asp:Button class="btn btn-primary button-style width-standard" runat="server" ID="add" Text="Add Hospital" OnClick="AddBtn_Click" />
                    </div>
                </div>
            </div>
        </div>
        </div>
        <div class="row margin-bottom">
            <div class="col-xs-12">
                <asp:GridView ID="gvHospitals" CssClass="table table-striped" AutoGenerateColumns="false" DataKeyNames="ID" ShowHeaderWhenEmpty="true" runat="server" OnRowCommand="gvHospital_RowCommand">
                    <Columns>
                        <asp:BoundField DataField="Name" HeaderText="Hospital" SortExpression="Name" />
                        <asp:BoundField DataField="Location" HeaderText="Location" SortExpression="Location" />
                        <asp:BoundField DataField="Contact" HeaderText="Contact" SortExpression="Contact" />


                    </Columns>
                </asp:GridView>
            </div>
        </div>
</asp:Content>