<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Appointments.aspx.cs" Inherits="Tabeeb.Appointments" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">

    <div class="jumbotron vertical-center">
        <h1>Appointments</h1>
    </div>
    <div class="row margin-bottom well" style="margin-bottom: 20px;">
        <div class="row margin-bottom">
            <div class="col-xs-4"></div>
            <div class="col-xs-4">
                <div class="row">
                    <div class="col-xs-4">
                        <label>Status : </label>
                    </div>
                    <div class="col-xs-4">
                        <asp:DropDownList runat="server" ID="status" >
                            <asp:ListItem Text="Pending" Value="0" />
                            <asp:ListItem Text="Approved" Value="1" />
                        </asp:DropDownList>
                    </div>
                    <div class="col-xs-4">
                        <asp:Button class="btn btn-primary" runat="server" ID="search" Text="Search Appointments" OnClick="search_Click" />
                    </div>
                </div>
            </div>
        </div>
        </div>
    <div class="row margin-bottom">
            <div class="col-xs-12">
                <asp:GridView ID="gvAppointments" CssClass="table table-striped" AutoGenerateColumns="false" DataKeyNames="ID" ShowHeaderWhenEmpty="true" runat="server" OnRowCommand="gvAppointments_RowCommand" >
                    <Columns>
                        <asp:BoundField DataField="userName" HeaderText="User Name" SortExpression="userName" />
                        <asp:BoundField DataField="docName" HeaderText="Dr Name" SortExpression="docName" />
                        <asp:BoundField DataField="appTime" HeaderText="Appointment Time" SortExpression="appTime" />
                        <asp:BoundField DataField="comment" HeaderText="Comment" SortExpression="comment" />
                        <asp:TemplateField HeaderText="Update">
                            <ItemTemplate>
                                <asp:LinkButton runat="server" CssClass="btn btn-primary" CommandName="editRec" Text="Update Status" CommandArgument="<%# Container.DataItemIndex %>"></asp:LinkButton>
                            </ItemTemplate>
                        </asp:TemplateField>
                        <asp:TemplateField HeaderText="Details">
                            <ItemTemplate>
                                <asp:LinkButton runat="server" CssClass="btn btn-info" CommandName="showDetails" Text="Details" CommandArgument="<%# Container.DataItemIndex %>"></asp:LinkButton>
                            </ItemTemplate>
                        </asp:TemplateField>
                    </Columns>
                </asp:GridView>
            </div>
        </div>
</asp:Content>
