<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var = "userlist" value = "${requestScope.USER_LIST}" />
<c:set var = "currentadmin" value = "${sessionScope.CURRENT_USER}" />

<c:if test = "${empty currentadmin}">
    <c:redirect url = "firstLoginPage"/>
</c:if>
<c:if test = "${currentadmin.role != 'A'}">
    Duma admin fake
    <script>window.history.back();</script>
    <%-- sau lày có lỗi thì đổi lại page 404. --%>
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
        <link rel="stylesheet" href="./styles/userListPageStyle.css" />
        <title>User List</title>
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
                        <input placeholder="Search..." />
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
                        <button><a href="/login.html">Create Category</a></button>
                    </div>
                    <div class="icon_notification_container">
                        <img src="./images/notification_icon.svg" />
                    </div>
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
                                    <a href="loadProfile?gmail=${currentadmin.email}"><p>Profile</p></a>
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
                        <a href="LoadAllPostsServlet"> <!-- cái tội không chịu chia ra 1 servlet riêng để handle empty url -->
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
                <div class="user-list-searchbar">
                    <input class="search-user" type="text" placeholder="Search..." />
                    <div class="user-list-icon">
                        <i class="fas fa-search"></i>
                    </div>
                </div>

                <div class="user-list-container" style = "height: 590px;">
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
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${loto.email}</td>
                                    <td>${loto.name}</td>
                                    <td>${loto.gender}</td>
                                    <td>${loto.campus}</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>${loto.statusaccount}</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                </c:forEach>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr><tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                <tr>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>in sao ke</td>
                                    <td>
                                        <select name="txtList" id="">
                                            <option value="Admin">Admin</option>
                                            <option value="Student">Student</option>
                                            <option value="Mentor">Mentor</option>
                                        </select>
                                    </td>
                                    <td>in sao ke</td>
                                    <td><button class="update-btn">Update</button></td>
                                    <td><button class="ban-btn">Ban</button></td>
                                </tr>
                                
                            </tbody>
                        </table>
                </div>
            </div>
        </div>

        <!-- footer -->
        <!-- footer -->
        <!-- footer -->
        <footer>
            <div class="container_footer">
                <p>
                    <span class="text_footer_strong">DEV Community</span> â A constructive
                    and inclusive social network for software developers. With you every
                    step of your journey.
                </p>
                <div style="margin: 0.25rem 0"></div>
                <p>
                    Built on <span class="text_footer_strong">Forem</span> â the
                    <span class="text_footer_strong">open source</span> software that
                    powers DEV and other inclusive communities
                </p>
            </div>
            <div class="text_footer_container">
                <p class="text_footer">
                    Made with
                    <i class="fa fa-heart" style="color: rgb(255, 70, 50)"></i> by
                    <span class="text_footer_strong">Ãn, An, Äan, Nam, PhÆ°Æ¡ng</span> Â©
                    2021
                </p>
                <img src="./images/forem_icon.svg" />
            </div>
        </footer>

        <!-- script   -->
        <!-- script   -->
        <!-- script   -->
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
