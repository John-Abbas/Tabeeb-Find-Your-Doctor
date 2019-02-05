<%@ Page Title="Doctors" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Add Doctor.aspx.cs" Inherits="Tabeeb.Add_Doctor" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <div class="jumbotron vertical-center">
        <h1>Doctors</h1>
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
                        <label>Specialization : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:DropDownList runat="server" ID="special" CssClass="width-standard">
                            <asp:ListItem Text="Physician" Value="0" />
                            <asp:ListItem Text="Neurologist" Value="1" />
                            <asp:ListItem Text="Cardiologist" Value="2" />
                            <asp:ListItem Text="Dentist" Value="3" />
                            <asp:ListItem Text="Gynecologist" Value="4" />
                            <asp:ListItem Text="Hematologist" Value="5" />
                            <asp:ListItem Text="ENT Specialist" Value="6" />
                            <asp:ListItem Text="Trichologist" Value="7" />
                        </asp:DropDownList>
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
        </div>

        <div class="row margin-bottom">

            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Fee : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:TextBox runat="server" ID="fee" TextMode="Number" type="number" CssClass="form-control width-standard" />
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Hours : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:TextBox runat="server" ID="hours" type="text" CssClass="form-control width-standard" />
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Age : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:TextBox runat="server" ID="age" TextMode="Number" type="number" CssClass="form-control width-standard" />
                    </div>
                </div>
            </div>
        </div>

        <div class="row margin-bottom">
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Gender : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:DropDownList runat="server" ID="gender" CssClass="width-standard">
                            <asp:ListItem Text="Male" Value="0" />
                            <asp:ListItem Text="Female" Value="1" />
                        </asp:DropDownList>
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
                            <asp:ListItem Text="Un-Verified" Value="1" />
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
                        <asp:Button class="btn btn-primary button-style width-standard" runat="server" ID="submit" Text="Add Doctor" OnClick="Unnamed1_Click" />
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="row margin-bottom">
        <div class="col-xs-12">
            <asp:GridView ID="gvStudents" CssClass="table table-striped" AutoGenerateColumns="false" DataKeyNames="ID" ShowHeaderWhenEmpty="true" runat="server" OnRowCommand="gvStudents_RowCommand">
                <Columns>
                    <asp:BoundField DataField="Name" HeaderText="Doctor" SortExpression="Name" />
                    <asp:BoundField DataField="specialName" HeaderText="Specialization" SortExpression="specialName" />
                    <asp:BoundField DataField="Location" HeaderText="Hospital" SortExpression="Location" />


                </Columns>
            </asp:GridView>

        </div>
    </div>
</asp:Content>
