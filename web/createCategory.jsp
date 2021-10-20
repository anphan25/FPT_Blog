<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="currentUser" value="${sessionScope.CURRENT_USER}" />
<c:set var="loginStatus" value="${sessionScope.LOGIN}" />
<c:if test="${empty currentUser}">
    <c:redirect url="notFoundPage" />
</c:if>
<c:if test="${currentUser.role != 'A'}">
    <c:redirect url="notFoundPage" />
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
        <link rel="stylesheet" href="./styles/createCategory.css" />
        <title>Create Category | FPT Blog</title>
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
                            <c:forEach var="cateDTO" items="${sessionScope.CATEGORY_LIST}">
                                <div class="dropdown_category_item">
                                    <c:url var="cateLink" value="searchByCategory">
                                        <c:param name="categoryId" value="${cateDTO.ID}" />
                                    </c:url>
                                    <a href="${cateLink}">${cateDTO.name}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="container_searchBar">
                        <form id="searchit" action="searchTitle">
                            <input placeholder="Search..." name="titleValue" />
                            <div class="container_icon" onclick="submit_form()">
                                <i class="fas fa-search"></i>
                            </div>
                        </form>
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
                        <button><a href="createCategoryPage">Create Category</a></button>
                    </div>
                    <div class="icon_notification_container">
                        <img src="./images/notification_icon.svg" />
                    </div>
                    <a href="messengerPage">
                        <div class="icon_notification_container">
                            <img src="./images/chat.svg" />
                        </div>
                    </a>
                    <div class="dropdown">
                        <div class="dropbtn">
                            <img src="${currentUser.avatar}" />
                        </div>
                        <div class="dropdown-content">
                            <div class="item-top">
                                <a
                                    ><h2>${currentUser.name}</h2>
                                    <p>@${currentUser.name}</p></a
                                >
                            </div>
                            <div style="padding: 0.5rem 0">
                                <div class="item">
                                    <c:url var="loadCurrentProfileLink" value="loadProfile">
                                        <c:param name="email" value="${currentUser.email}" />
                                    </c:url>
                                    <a href="${loadCurrentProfileLink}"><p>Profile</p></a>
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
                            <span class="brand_text">DEV Community</span> is a community of
                            690,628 amazing developers
                        </h2>
                        <p class="introduce_content">
                            We're a place where coders share, stay up-to-date and grow their
                            careers.
                        </p>
                        <div class="container_button">
                            <div class="container_button_register">
                                <button><a href="/login.html">Táº¡o tÃ i khoáº£n</a></button>
                            </div>
                            <div class="container_button_login">
                                <button><a href="/login.html">ÄÄng nháº­p</a></button>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar_navigation">
                        <h2 class="title_navigation">Menu</h2>
                        <div class="container_item">
                            <img src="./images/house_icon.svg" />
                            <p>Trang chá»§</p>
                        </div>
                        <div class="container_item">
                            <img src="./images/hand_shake_icon.svg" />
                            <p>ÄÄng nháº­p</p>
                        </div>
                    </div>
                    <div class="sidebar_navigation">
                        <h2 class="title_navigation">Tags phá» biáº¿n</h2>
                        <div class="container_item">
                            <p>#nodejs</p>
                        </div>
                        <div class="container_item">
                            <p>#python</p>
                        </div>
                        <div class="container_item">
                            <p>#devops</p>
                        </div>
                        <div class="container_item">
                            <p>#angular</p>
                        </div>
                        <div class="container_item">
                            <p>#vuejs</p>
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
                            <div class="container_item user-list-icon">
                                <img src="./images/user-list.svg" />
                                <p>User List</p>
                            </div>
                        </a>
                        <a href="loadAllComments">
                            <div class="container_item user-list-icon">
                                <img src="./images/comment.png" />
                                <p>Comments Management</p>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="main-container">
                <h1>Category List</h1>
                <form action="createCategory">
                    <input
                        class="add-input"
                        type="text"
                        placeholder="Input a category ..."
                        name="txtCategory"
                        />
                    <button class="create-btn" type="submit" value="Create">
                        Create
                    </button>
                </form>

                <div class="category-list">
                    <table>
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Category Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach
                                var="cateDTO"
                                items="${sessionScope.CATEGORY_LIST}"
                                varStatus="counter"
                                >
                                <tr>
                                    <td><p class="title">${counter.count}</p></td>
                                    <td><p class="wrapper">${cateDTO.name}</p></td>
                                </tr>
                            </c:forEach>
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
        </script>
    </body>
</html>
