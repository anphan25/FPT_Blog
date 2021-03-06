<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>


        <c:set var="userlist" value="${requestScope.USER_LIST}" />
        <c:set var="currentadmin" value="${sessionScope.CURRENT_USER}" />
        <c:set var="major" value="${sessionScope.CATEGORY_LIST}" />
        <c:set var="select" value="${requestScope.SELECTION_TEXT}" />
        <c:set var="success" value="${requestScope.NOTIFY}" />

        <c:if test="${empty currentadmin}">
            <c:redirect url="firstLoginPage" />
        </c:if>
        <c:if test="${currentadmin.role != 'A'}">
            <script>window.history.back();</script>
            <%-- sau lày có lỗi thì đổi lại page 404. --%>
        </c:if>
        <c:if test="${empty userlist}">
            <c:redirect url="loadUserList" />
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
                rel="stylesheet" />
            <script src="https://kit.fontawesome.com/1b1fb57155.js" crossorigin="anonymous"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
                                <img src="https://i.chungta.vn/2017/12/22/LogoFPT-2017-copy-3042-1513928399.jpg" />
                            </a>
                        </div>
                        <div class="dropdown_category">
                            <div class="container_category">
                                <p>Categories</p>
                            </div>
                            <div class="dropdown_category_content">
                                <c:forEach var="cateDTO" items="${sessionScope.CATEGORY_LIST}">
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
                        <div class="dropdown">
                            <div class="dropbtn_noti">
                                <img src="./images/notification_icon.svg" />
                                <div id="warning" class="warning warning-hidden">!</div>
                            </div>
                            <div class="dropdown-content1">
                            </div>
                        </div>
                        <a href="messengerPage">
                            <div class="icon_notification_container">
                                <img src="./images/chat.svg" />
                            </div>
                        </a>
                        <div class="dropdown">
                            <div class="dropbtn">
                                <img src="${currentadmin.avatar}" />
                            </div>
                            <div class="dropdown-content">
                                <div class="dropdown-content">
                                    <div class="item-top">
                                        <a>
                                            <h2>${currentadmin.name}</h2>
                                            <p>@${currentadmin.campus}</p>
                                        </a>
                                    </div>
                                    <div style="padding: 0.5rem 0">
                                        <div class="item">
                                            <a href="loadProfile?email=${currentadmin.email}">
                                                <p>Profile</p>
                                            </a>
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
                            <a href="loadUserList">
                                <div class="container_item create-post">
                                    <img src="./images/user-list.svg" />
                                    <p>User List</p>
                                </div>
                            </a>
                            <a href="loadAllComments">
                                <div class="container_item create-post">
                                    <img src="./images/comment.png" />
                                    <p>Comments Management</p>
                                </div>
                            </a>
                            <a href="loadAwardStandard">
                                <div class="container_item create-post">
                                    <img src="./images/adjust_icon.png" />
                                    <p>Award Adjustment</p>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="container-item">
                    <h1>List of users</h1>
                    <c:if test="${empty select}">
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
                            <div class="dropdown_filt">
                                <div class="select_filt">
                                    <span>Select Role</span>
                                </div>
                                <input type="hidden" id="selectedrole">
                                <ul class="dropdown-menu" onclick="ShowMajor()">
                                    <li id="S">Student</li>
                                    <li id="M">Mentor</li>
                                    <li id="A">Admin</li>
                                    <li id="all">View all</li>
                                    <!-- show all when it choose the third one -->
                                </ul>
                            </div>
                            <div class="dropdown_filt" id="hiddenmajor">
                                <div class="select_filt">
                                    <span>Select Major</span>
                                </div>
                                <input type="hidden" id="selectedmajor">
                                <ul class="dropdown-menu">
                                    <c:forEach var="categories" items="${major}">
                                        <li id="${categories.name}">${categories.name}</li>
                                    </c:forEach>
                                    <!-- show all when it choose the third one -->
                                </ul>
                            </div>
                            <button id="zawarudo" class="button_filt" onclick="applyButton()">Apply</button>
                        </div>
                    </c:if>
                    <c:if test="${not empty select}">
                        <div class="Filtering">
                            <div class="dropdown_filt">
                                <div class="select_filt">
                                    <span>
                                        <c:if test="${select[0] == 'all'}">
                                            View all
                                        </c:if>
                                        <c:if test="${select[0] != 'all'}">
                                            ${select[0]}
                                        </c:if>
                                    </span>
                                </div>
                                <input type="hidden" id="selectedgender" value="${select[0]}">
                                <ul class="dropdown-menu">
                                    <li id="Male">Male</li>
                                    <li id="Female">Female</li>
                                    <li id="all">View all</li>
                                    <!-- show all when it choose the third one -->
                                </ul>
                            </div>
                            <div class="dropdown_filt">
                                <div class="select_filt">
                                    <span>
                                        <c:if test="${select[1] == 'all'}">
                                            View all
                                        </c:if>
                                        <c:if test="${select[1] != 'all'}">
                                            ${select[1]}
                                        </c:if>
                                    </span>
                                </div>
                                <input type="hidden" id="selectedstatus" value="${select[1]}">
                                <ul class="dropdown-menu">
                                    <li id="Actived">Active</li>
                                    <li id="Banned">Banned</li>
                                    <li id="all">View all</li>
                                </ul>
                            </div>
                            <div class="dropdown_filt">
                                <div class="select_filt">
                                    <span>
                                        <c:if test="${select[2] == 'all'}">
                                            View all
                                        </c:if>
                                        <c:if test="${select[2] != 'all'}">
                                            ${select[2]}
                                        </c:if>
                                    </span>
                                </div>
                                <input type="hidden" id="selectedrole" value="${select[2]}">
                                <ul class="dropdown-menu" onclick="ShowMajor()">
                                    <li id="S">Student</li>
                                    <li id="M">Mentor</li>
                                    <li id="A">Admin</li>
                                    <li id="all">View all</li>
                                    <!-- show all when it choose the third one -->
                                </ul>
                            </div>
                            <div class="dropdown_filt" id="hiddenmajor">
                                <div class="select_filt">
                                    <span>
                                        <c:if test="${select[3] == 'all'}">
                                            View all
                                        </c:if>
                                        <c:if test="${select[3] != 'all'}">
                                            ${select[3]}
                                        </c:if>
                                    </span>
                                </div>
                                <input type="hidden" id="selectedmajor" value="${select[3]}">
                                <ul class="dropdown-menu">
                                    <c:forEach var="categories" items="${major}">
                                        <li id="${categories.name}">${categories.name}</li>
                                    </c:forEach>
                                    <li id="all">View all</li>
                                    <!-- show all when it choose the third one -->
                                </ul>
                            </div>
                            <button id="zawarudo" class="button_filt" onclick="applyButton()">Apply</button>
                        </div>
                    </c:if>
                    <form onsubmit="SendData();return false">
                        <div class="user-list-searchbar">
                            <input id="searchtext" class="search-user" type="text" placeholder="Search email"
                                name="txtSearch" value="${requestScope.SEARCH_TEXT}" />
                            <div class="user-list-icon" onclick="SendData()">
                                <i class="fas fa-search"></i>
                            </div>
                        </div>
                    </form>
                    <div id="reloading" class="user-list-container" style="height: 590px;">
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
                                    <c:forEach var="loto" items="${userlist}" varStatus="counter">
                                        <form id="form${counter.count}" class="user-form" onsubmit="return false;">
                                            <tr>
                                                <td class="count">${counter.count}</td>
                                                <td class="email-td"><a
                                                        href="loadProfile?email=${loto.email}">${loto.email}</a></td>
                                                <input type="hidden" id="victimEmail" value="${loto.email}" />
                                                <td class="td-name">${loto.name}</td>
                                                <td>
                                                    <c:if test="${loto.gender == 'Male'}">
                                                        <i class="fas fa-mars"></i>
                                                    </c:if>
                                                    <c:if test="${loto.gender == 'Female'}">
                                                        <i class="fas fa-venus"></i>
                                                    </c:if>
                                                </td>
                                                <td class="campusUser">${loto.campus}</td>
                                                <td>
                                                    <select id="txtList">
                                                        <c:if test="${loto.role == 'Admin'}">
                                                            <option selected="selected" value="A">Admin</option>
                                                            <option value="S">Student</option>
                                                            <option value="M">Mentor</option>
                                                        </c:if>
                                                        <c:if test="${loto.role == 'Mentor'}">
                                                            <option value="A">Admin</option>
                                                            <option value="S">Student</option>
                                                            <option selected="selected" value="M">Mentor</option>
                                                        </c:if>
                                                        <c:if test="${loto.role == 'Student'}">
                                                            <option value="A">Admin</option>
                                                            <option selected="selected" value="S">Student</option>
                                                            <option value="M">Mentor</option>
                                                        </c:if>
                                                    </select>
                                                </td>
                                                <td class="banAcStatus">
                                                    <c:if test="${loto.statusaccount == 'Actived'}">
                                                        <p class="activeStatus">Active</p>
                                                    </c:if>
                                                    <c:if test="${loto.statusaccount == 'Banned'}">
                                                        <p class="bannedStatus">Banned</p>
                                                    </c:if>
                                                </td>
                                                <td><button class="update-btn" title="Update Account" name="btAction"
                                                        value="updating"
                                                        onclick="updateButton('form${counter.count}')"><i
                                                            class="fas fa-pen"></i></button></td>
                                                <td>
                                                    <c:if test="${loto.statusaccount == 'Actived'}">
                                                        <button class="ban-btn" title="Ban Account" name="btAction"
                                                            value="banning"
                                                            onclick="banButton('form${counter.count}')"><i
                                                                class="fas fa-user-slash"></i></button>
                                                    </c:if>
                                                    <c:if test="${loto.statusaccount == 'Banned'}">
                                                        <button class="unban-btn" title="Unban Account" name="btAction"
                                                            value="unbaning"
                                                            onclick="unbanButton('form${counter.count}')"><i
                                                                class="fas fa-user-check"></i></button>
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


            <!-- modal  -->
            <!-- modal  -->
            <!-- modal  -->
            <div class="ban-modal hidden" id="banmodal">
                <h1>Please input the reason why you ban this user</h1>
                <textarea name="" id="ban-area" cols="30" rows="10" placeholder="Input the reason" value=""></textarea>
                <div class="ban-btn-gr">
                    <button class="ban-btn-modal" onclick="submitBan()">Ban</button>
                    <button class="cancel-btn-ban-modal">Cancel</button>
                </div>
            </div>
            <div class="ban-overlay hidden" id="banoverlay"></div>


            <!-- updateRole-modal -->
            <!-- updateRole-modal -->
            <!-- updateRole-modal -->
            <div class="updateRole-modal hidden" id="updatemodal">
                <h1>Select the Category this mentor will manage</h1>
                <select name="" id="select-category">
                    <c:forEach var="cateDTO" items="${sessionScope.CATEGORY_LIST}">
                        <option value="${cateDTO.ID}">${cateDTO.name}</option>
                    </c:forEach>
                </select>
                <div class="updateRole-btn-gr">
                    <button class="updateRole-btn-modal" onclick="updateForMentor()">Update</button>
                    <button class="cancel-btn-updateRole-modal">Cancel</button>
                </div>
            </div>
            <div class="updateRole-overlay hidden" id="updateoverlay"></div>
            <!-- update successfully -->
            <!-- update successfully -->
            <!-- update successfully -->
            <!--        <div class="update-successfully-modal hidden" id="updatesuccess" >
                    <img src="./images/success-icon.jpg" alt="">
                    <h1>Updating Successfully !!!</h1> 
                    <button class="OKbtn-update-successfully">Ok</button>
                </div>
                <div class="update-successfully-overlay hidden" id="updatesuccessfully"></div>-->

            <!-- ban successfully -->
            <!-- ban successfully -->
            <!-- ban successfully -->
            <!--        <div class="ban-successfully-modal hidden" id="bansuccess" >
                    <img src="./images/success-icon.jpg" alt="">
                    <h1>Banning Successfully !!!</h1> 
                    <button class="OKbtn-ban-successfully">Ok</button>
                </div>
                <div class="ban-successfully-overlay hidden" id="bansuccessfully"></div>-->

            <!-- unban successfully -->
            <!-- unban successfully -->
            <!-- unban successfully -->
            <!--        <div class="unban-successfully-modal hidden" id="unbansuccess">
                    <img src="./images/success-icon.jpg" alt="">
                    <h1>Unbanning Successfully !!!</h1>
                    <button class="OKbtn-unban-successfully">Ok</button>
                </div>
                <div class="unban-successfully-overlay hidden" id="unbansuccessfully" ></div>-->

            <!-- footer -->
            <!-- footer -->
            <!-- footer -->
            <footer>
                <div class="container_footer">

                    <div class="cmp-text">
                        <p class="text_footer">
                            <span class="text_footer_strong">
                                <a href="https://legal.web.com" />Legal</a>
                            </span>
                            &nbsp;&nbsp;|&nbsp;&nbsp;
                            <span class="text_footer_strong">
                                <a href="https://www.fpt-software.com/privacy-statement/" />Privacy Policy</a>
                            </span>
                            &nbsp;&nbsp;|&nbsp;&nbsp;
                            <span class="text_footer_strong">
                                <a href="https://www.fpt-software.com/terms-of-use/" />Terms of Use</a>
                            </span>
                            &nbsp;&nbsp;|&nbsp;&nbsp;
                            <span class="text_footer_strong">
                                <a href="https://www.fpt-software.com/contact-us/" />Contact Us</a>
                            </span>
                            &nbsp;&nbsp;|&nbsp;&nbsp;
                            <span class="text_footer_strong">
                                <a href="https://blog.fpt-software.com" />Technology Blog</a>
                            </span>
                        </p>
                    </div>
            </div>
            <div style="margin: 0.25rem 0"></div>
            <div>
                <div class="cmp-text">
                    <p class="text_footer">
                    <div class="text_footer_container">
                            © Copyright 2021 FPT University. All rights reserved. &nbsp;
                            <br>
                            All registered trademarks herein are the property of their respective owners.
                        <img src="./images/fpt-university.png" />
                    </div>
                    </p>
                </div>
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
                function submit_form() {
                    var form = document.getElementById("searchit");
                    form.submit();
                }
                document.getElementById("hiddenmajor").style.display = "none";
                function ShowMajor() {
                    if (document.getElementById("selectedrole").value === "M")
                        document.getElementById("hiddenmajor").style.display = "inline-block";
                    else
                        document.getElementById("hiddenmajor").style.display = "none";
                }
                if (window.history.replaceState) {
                    window.history.replaceState(null, null, window.location.href);
                }
            <c:if test="${not empty select}">
                <c:if test="${select[2] == 'Mentor'}">
                    document.getElementById("hiddenmajor").style.display = "inline-block";
                </c:if>
            </c:if>
            <c:if test="${not empty success}">
                <c:choose>
                    <c:when test = "${success == 'Updating'}">
                    swal({
                        title: "Updating Successfully!",
                        icon: "success",
                        button: "Ok!",
                    });
//                    $("#updatesuccess").removeClass("hidden");
//                    $("#updatesuccessfully").removeClass("hidden");
                    </c:when>
                    <c:when test = "${success == 'Banning'}">
                    swal({
                        title: "Banning Successfully!",
                        icon: "success",
                        button: "Ok!",
                    });
//                    $("#bansuccess").removeClass("hidden");
//                    $("#bansuccessfully").removeClass("hidden");
                    </c:when>
                    <c:when test = "${success == 'Unbanning'}">
                    swal({
                        title: "Unbanning Successfully!",
                        icon: "success",
                        button: "Ok!",
                    });
//                    $("#unbansuccess").removeClass("hidden");
//                    $("#unbansuccessfully").removeClass("hidden");
                    </c:when>
                </c:choose>
            </c:if>

            </script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
            <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-firestore.js"></script>
            <script>
                // Initialize Firebase
                firebase.initializeApp({
                    apiKey: 'AIzaSyAPgZZxNDsNeVB-C6hMGKzsFelsBRIjdBI',
                    authDomain: 'udemy-vue-firebase-si.firebaseapp.com',
                    projectId: 'udemy-vue-firebase-si',
                });
                const db = eval('firebase.firestore()');
                const notiWrapper = document.querySelector(".dropdown-content1");
                let lastestNotiCreatedAt = "";
                let componentRunOnDepend = false;
                let lol = false;
                let currentUser = `${currentadmin.email}`;
                currentUser = currentUser.substr(0, currentUser.indexOf("@"));
                const itemNoti = (avatar, user, action, postID, createdAt) => {
                    return (
                        ` <a href="loadPostContent?postId=\${postID}">
                            <div class="noti_item">
                                <img class="noti_other_user"  src="\${avatar}"/>
                                  <div>
                                     <p><b>\${user}</b> \${action} your post</p>
                                    <p style="font-size: 14px; margin-top: 0.2rem">\${createdAt}</p>
                                  </div>
                            </div>
                        </a>`
                    )
                }

                const itemNotiNew = (avatar, user, action, postID, createdAt) => {
                    return (
                        ` <a href="loadPostContent?postId=\${postID}">
                            <div class="noti_item_new">
                                <img class="noti_other_user"  src="\${avatar}"/>
                                  <div>
                                    <p><b>\${user}</b> \${action} your post</p>
                                    <p style="font-size: 14px; margin-top: 0.2rem">\${createdAt}</p>
                                  </div>
                            </div>
                        </a>`
                    )
                }

                $(".dropbtn_noti").hover(function (e) {
                    $("#warning").addClass("warning-hidden");
                });
                // Functions
                const componentDidMount = (function () {
                    let ref = false;
                    return function () {
                        if (!ref) {
                            ref = true;
                            componentRunOnDepend = true;
                            getDocumentOnMount();
                        }
                    };
                })();

                // useEffect
                componentDidMount();

                async function getDocumentOnMount() {
                    let domMessage = '';
                    let notifyRealtime = [];
                    await db
                        .collection('notify')
                        .doc(currentUser)
                        .collection("incoming")
                        .orderBy('createdAt', 'desc')
                        .limit(5)
                        .get()
                        .then((querySnapshot) => {
                            querySnapshot.forEach((doc) => {
                                notifyRealtime.push(doc.data());
                            });
                        })
                        .catch((error) => {
                            console.log('Error getting documents: ', error);
                        });
                    if (notifyRealtime.length > 0) {
                        notifyRealtime.forEach((doc, index) => {
                            if (doc.createdAt) {
                                if (index === notifyRealtime.length - 1) {
                                    lastestNotiCreatedAt = doc.createdAt.seconds;
                                }
                                var date = new Date(doc.createdAt.toDate()).toLocaleString("en-GB", { year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit' });
                                domMessage += itemNoti(doc.avatar, doc.user, doc.action, doc.postId, date);

                            }
                        });
                    } else {
                        domMessage += `<div class="noti_item">
                                        <p></p>
                                    </div>
                                </div>`;
                    }
                    notiWrapper.innerHTML = domMessage;
                }

                if (componentRunOnDepend) {
                    db.collection('notify')
                        .doc(currentUser)
                        .collection("incoming")
                        .orderBy('createdAt', 'desc')
                        .limit(1)
                        .onSnapshot((querySnapshot) => {
                            let domMessage = '';
                            let notifyRealtime = [];
                            querySnapshot.forEach((doc) => {
                                if (doc.exists) {
                                    let id = doc.id;
                                    let data = { ...doc.data(), id };
                                    notifyRealtime.push(data);
                                }
                            });
                            notifyRealtime.forEach((doc, index) => {
                                if (doc.createdAt) {
                                    console.log(lastestNotiCreatedAt, doc.createdAt.seconds);
                                    if (lol) {
                                        var date = new Date(doc.createdAt.toDate()).toLocaleString("en-GB", { year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit' });
                                        domMessage += itemNotiNew(doc.avatar, doc.user, doc.action, doc.postId, date);
                                    }
                                    lol = true;
                                }
                            });
                            if (domMessage !== '') {
                                notiWrapper.insertAdjacentHTML('afterbegin', domMessage);
                                $("#warning").removeClass("warning-hidden");
                            }
                        });
                }
            </script>
        </body>

        </html>