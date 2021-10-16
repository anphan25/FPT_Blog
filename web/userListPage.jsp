<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<c:set var = "userlist" value = "${requestScope.USER_LIST}" />
<c:set var = "currentadmin" value = "${sessionScope.CURRENT_USER}" />

<c:if test = "${empty currentadmin}">
    <c:redirect url = "firstLoginPage"/>
</c:if>
<c:if test = "${currentadmin.role != 'A'}">
    <script>window.history.back();</script>
    <%-- sau lày có lỗi thì đổi lại page 404. --%>
</c:if>
<c:if test = "${empty userlist}">
    <c:redirect url = "loadUserList"/>
    <%-- tự tôi tìm ra lỗi tự tôi fix. huhu --%>
</c:if>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
            rel="stylesheet"
            />
        <script
            src="https://kit.fontawesome.com/1b1fb57155.js"
            crossorigin="anonymous"
        ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link rel="stylesheet" href="./styles/userListPageStyle.css" />
        <title>User List | FPT Blog</title>
    </head>
    <body>
        <!-- header  -->
        <!-- header  -->
        <!-- header  -->
        <header>
            <div class="container_header">
                <div class="container_left">
                    <div class="toggle_sidebar" onclick="toggleSidebarPhone()">
                        <img src="./images/toggle_sidebar_icon.svg" />
                    </div>
                    <div class="container_logo">
                        <a href="loadBlogs">
                            <img
                                src="https://i.chungta.vn/2017/12/22/LogoFPT-2017-copy-3042-1513928399.jpg"
                                />
                        </a>
                    </div>
                    <div class="dropdown_category">
                        <div class="container_category">
                            <p>Categories</p>
                        </div>
                        <div class="dropdown_category_content">
                            <c:forEach var="cateDTO" items="${sessionScope.CATEGORY_LIST}" >
                                <div class="dropdown_category_item">
                                    <a href="searchByCategory?categoryId=${cateDTO.ID}">${cateDTO.name}</a>
                                </div>  
                            </c:forEach>
                        </div>
                    </div>
                    <div class="container_searchBar">
                        <input placeholder="Search..." name="titleValue" />
                        <div class="container_icon" onclick="submit_form()">
                            <i class="fas fa-search"></i>
                        </div>
                    </div>
                </div>
                <!-- <div class="container_right">
                  <div class="container_button_login">
                    <button><a href="/login.html">ÄÄng nháº­p</a></button>
                  </div>
                  <div class="container_button_register">
                    <button><a href="/login.html">Táº¡o tÃ i khoáº£n</a></button>
                  </div>
                </div> -->
                <div class="container_right">
                    <div class="container_button_register">
                        <button><a href="https://youtu.be/rvrZJ5C_Nwg?t=127">Create Category</a></button>
                    </div>
                    <div class="icon_notification_container" onclick="kingcrimson()">
                        <img src="./images/notification_icon.svg" />
                    </div>
                    <a href="messengerPage">
                        <div class="icon_notification_container">
                            <img src="./images/chat.svg" />
                        </div>
                    </a>
                    <div class="dropdown">
                        <div class="dropbtn">
                            <img
                                src="${currentadmin.avatar}"
                                />
                        </div>
                        <div class="dropdown-content">
                            <div class="dropdown-content">
                                <div class="item-top">
                                    <a
                                        ><h2>${currentadmin.name}</h2>
                                        <p>@${currentadmin.campus}</p></a
                                    >
                                </div>
                                <div style="padding: 0.5rem 0">
                                    <div class="item">
                                        <a href="loadProfile?email=${currentadmin.email}"><p>Profile</p></a>
                                    </div>
                                    <div class="item">
                                        <a><p>Create Post</p></a>
                                    </div>
                                </div>
                                <div class="item-bottom">
                                    <a href="logout">Sign Out</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </header>

        <!-- sidebar_phone -->
        <!-- sidebar_phone -->
        <!-- sidebar_phone -->
        <!-- quá buồn khi công sức của front end bị Ân đạp đổ bể
        <section class="sidebar_phone" id="sidebar_phone">       
            <div class="container_sidebar_phone" id="container_sidebar_phone">
                <div class="container_toggle">
                    <h2 class="title">FPT Blog</h2>
                    <img
                        src="./images/close_button_icon.svg"
                        onclick="handleClickOutside()"
                        />
                </div>
                <div style="padding: 0.5rem">
                    <div class="introduce_community">
                        <h2 class="introduce_title">
                            <span class="brand_text">User Control Panel</span> one of the powerful tool to prevent
                            chaos
                        </h2>
                        <p class="introduce_content">
                            We're a place where coders share, stay up-to-date and grow their
                            careers.
                        </p>
                        <div class="container_button">
                            <div class="container_button_register">
                                <button><a href="/login.html">Kiểm tra log</a></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div
                class="outside_sidebar_phone"
                id="outside_sidebar_phone"
                onclick="handleClickOutside()"
                ></div>
        </section>
        -->

        <!-- container  -->
        <!-- container  -->
        <!-- container  -->
        <div class="container">
            <div class="left-menu">
                <div class="navigation_left">
                    <div class="sidebar_navigation">
                        <h2 class="title_navigation">Menu</h2>
                        <a href="loadBlogs">
                            <div class="container_item">
                                <img src="./images/house_icon.svg" />
                                <p>Home</p>
                            </div>
                        </a>
                        <a href="createCategoryPage">
                            <div class="container_item">
                                <img src="./images/category_icon.svg" />
                                <p>Create Category</p>
                            </div>
                        </a>
                        <a href="">
                            <div class="container_item">
                                <img src="./images/list_icon.svg" />
                                <p>Check Log(under maintain)</p>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="container-item">
                <h1>List of users</h1>
                <div class="Filtering">
                    <div class="dropdown_filt">
                        <div class="select_filt">
                            <span>Select Gender</span>
                        </div>
                        <input type="hidden" id="selectedgender">
                        <ul class="dropdown-menu">
                            <li id="Male">Male</li>
                            <li id="Female">Female</li>
                            <li id="all">View all</li>
                            <!-- show all when it choose the third one -->
                        </ul>
                    </div>
                    <div class="dropdown_filt">
                        <div class="select_filt">
                            <span>Select Status</span>
                        </div>
                        <input type="hidden" id="selectedstatus">
                        <ul class="dropdown-menu">
                            <li id="Actived">Active</li>
                            <li id="Banned">Banned</li>
                            <li id="all">View all</li>
                        </ul>
                    </div>
                    <button id="zawarudo" class="button_filt" onclick="applyButton()" >Apply</button>
                </div>
                <form onsubmit="SendData();return false">
                    <div class="user-list-searchbar">
                        <input id="searchtext" class="search-user" type="text" placeholder="Search email" name="txtSearch" value="${param.txtSearch}"/>
                        <div class="user-list-icon" onclick="SendData()">
                            <i class="fas fa-search"></i>
                        </div>
                    </div>
                </form>
                <div id="reloading" class="user-list-container" style = "height: 590px;">
                    <div id="freshair">
                        <table>
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Email</th>
                                    <th>Fullname</th>
                                    <th>Gender</th>
                                    <th>Campus</th>
                                    <th>Role</th>
                                    <th>Status</th>
                                    <th colspan="2">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var = "loto" items = "${userlist}" varStatus = "counter">
                                <form action='userAction'>
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td><a href="loadProfile?email=${loto.email}">${loto.email}</a></td>
                                    <input type="hidden" name="victimEmail" value="${loto.email}" />
                                    <td class="td-name">${loto.name}</td>
                                    <td>${loto.gender}</td>
                                    <td>${loto.campus}</td>
                                    <td>
                                        <select name="txtList" >
                                            <c:if test = "${loto.role == 'Admin'}" >
                                                <option selected="selected" value="A">Admin</option>
                                                <option value="S">Student</option>
                                                <option value="M">Mentor</option>
                                            </c:if>
                                            <c:if test = "${loto.role == 'Mentor'}" >
                                                <option value="A">Admin</option>
                                                <option value="S">Student</option>
                                                <option selected="selected" value="M">Mentor</option>
                                            </c:if>
                                            <c:if test = "${loto.role == 'Student'}" >
                                                <option value="A">Admin</option>
                                                <option selected="selected" value="S">Student</option>
                                                <option value="M">Mentor</option>
                                            </c:if>
                                        </select>
                                    </td>
                                    <td>${loto.statusaccount}</td>
                                    <td><button id="submitanlz" class="update-btn" name="btAction" value="updating" >Update</button></td>
                                    <td>
                                        <c:if test="${loto.statusaccount == 'Actived'}">
                                            <button class="ban-btn" name="btAction" value="banning" >Ban</button>
                                        </c:if>
                                        <c:if test="${loto.statusaccount == 'Banned'}">
                                            <button class="ban-btn" name="btAction" value="unbaning" >Unban</button>
                                        </c:if>
                                    </td>    
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- footer -->
        <!-- footer -->
        <!-- footer -->
        <footer>
            <div class="container_footer">
                <p>
                    <span class="text_footer_strong">DEV Community</span> - A constructive
                    and inclusive social network for software developers. With you every
                    step of your journey.
                </p>
                <div style="margin: 0.25rem 0"></div>
                <p>
                    Built on <span class="text_footer_strong">Forem</span> - the
                    <span class="text_footer_strong">open source</span> software that
                    powers DEV and other inclusive communities
                </p>
            </div>
            <div class="text_footer_container">
                <p class="text_footer">
                    Made with
                    <i class="fa fa-heart" style="color: rgb(255, 70, 50)"></i> by
                    <span class="text_footer_strong">Ân, An, Đan, Nam, Phương</span> ©
                    2021
                </p>
                <img src="./images/forem_icon.svg" />
            </div>
        </footer>

        <!-- script   -->
        <!-- script   -->
        <!-- script   -->
        <script src="./js/userListPage.js"></script>
        <script>
            function toggleSidebarPhone() {
                const toggle_sidebar = document.getElementById("sidebar_phone");
                toggle_sidebar.style.display = "block";
            }
            function handleClickOutside() {
                const toggle_sidebar = document.getElementById("sidebar_phone");
                toggle_sidebar.style.display = "none";
            }
            function submit_form()
            {
                var form = document.getElementById("searchit");
                form.submit();
            }
        </script>
    </body>
</html>
