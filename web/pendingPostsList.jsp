<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="loginStatus" value="${sessionScope.LOGIN}"/>
<c:set var="currentUser" value="${sessionScope.CURRENT_USER}"/>
<c:set var="pendingList" value="${requestScope.PENDING_LIST}"/>
<c:if test="${empty currentUser}">
    <c:redirect url="notFoundPage" />
</c:if>
<c:if test="${currentUser.role != 'M'}">
    <c:redirect url="notFoundPage" />
</c:if>
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
            src="https://kit.fontawesome.com/03ade0a214.js"
            crossorigin="anonymous"
        ></script>

        <link rel="stylesheet" href="./styles/pendingPostsList.css" />
        <title>Pending Posts | FPT Blog</title>
    </head>
    <body>
        <!-- header  -->
        <!-- header  -->
        <!-- header  -->
        <header >
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
                                    <c:url var="cateLink" value="searchByCategory">
                                        <c:param name="categoryId" value="${cateDTO.ID}"/>
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
                    <script>
                        function submit_form()
                        {
                            var form = document.getElementById("searchit");
                            form.submit();
                        }
                    </script>
                </div>
                <!-- <div class="container_right">
                  <div class="container_button_register">
                    <button>
                      <a href="/login.html">Pending Posts </a>
                      <div class="num-post"><span>3</span></div>
                    </button>
                  </div>
                  <div>Avatar</div>
                </div> -->
                <div class="container_right">
                    <div class="container_button_register">
                        <button><a href="loadPendingPosts?postStatus=WFA">Pending Post</a>
                        </button>
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
                            <img
                                src="${currentUser.avatar}"
                                />
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
                                    <a href="loadProfile?email=${currentUser.email}"><p>Profile</p></a>
                                </div>
                            </div>
                            <div class="item-bottom">
                                <a  href="logout">Sign Out</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- Main -->

        <section class="main">
            <div class="container">
                <!-- <div class="title">
                    <h1>Pending Posts</h1>
                  </div> -->
                <div class="row">

                    <div class="navigation_left">
                        <div class="sidebar_navigation">
                            <h2 class="title_navigation">Menu</h2>
                            <a href="loadBlogs">
                                <div class="container_item">
                                    <img src="./images/house_icon.svg" />
                                    <p>Home</p>
                                </div>
                            </a>
                            <a href="loadPendingPosts?postStatus=WFA">
                                <div class="container_item">
                                    <img src="./images/list_icon.svg" />
                                    <p>Pending Posts</p>
                                </div>
                            </a>
                            <a href="createPostPage">
                                <div class="container_item create-post">
                                    <img src="./images/create-blog.svg" />
                                    <p>Create Post</p>
                                </div>
                            </a>
                            <a href="loadDashboard">
                                <div class="container_item create-post">
                                    <img src="./images/dashborad.svg" />
                                    <p>Give Award</p>
                                </div>
                            </a>
                            <!-- <a href="">
                              <div class="container_item">
                                <img class="item-trophy-icon" src="./images/trophy_icon.svg" />
                                <p>Give award</p>
                              </div>
                            </a> -->
                        </div>
                    </div>
                    <div class="navigation_right">
                        <div class="title">
                            <h1>Pending posts</h1>
                        </div>
                        <div class="pending_tab">

                            <a href="loadPendingPosts?postStatus=WFA">
                                <p class="tab_item <c:if test="${requestScope.TAB_STATUS == 'WFA'}">tab_active</c:if>">Public Request</p>
                                </a>


                                <a href="loadPendingPosts?postStatus=WFU">
                                    <p class="tab_item <c:if test="${requestScope.TAB_STATUS == 'WFU'}">tab_active</c:if>">Update Request</p>
                                </a>


                                <a href="loadPendingPosts?postStatus=WFD">
                                    <p class="tab_item <c:if test="${requestScope.TAB_STATUS == 'WFD'}">tab_active</c:if>">Delete Request</p>
                                </a>

                            </div>
                            <div class="pending_posts">
                            <c:if test="${not empty pendingList}">
                                <c:forEach var="pendingDTO" items="${pendingList}">

                                    <div class="post">

                                        <div class="container_info_post">
                                            <div class="post-status">

                                                <c:if test="${pendingDTO.statusPost == 'WFA'}">
                                                    <h1>Public Request</h1>
                                                </c:if>
                                                <c:if test="${pendingDTO.statusPost == 'WFD'}">
                                                    <h1>Delete Request</h1>
                                                </c:if>
                                                <c:if test="${pendingDTO.statusPost == 'WFU'}">
                                                    <h1>Update Request</h1>
                                                </c:if>
                                            </div>
                                            <div class="user_info">
                                                <div class="container_avatar">
                                                    <img
                                                        src="${pendingDTO.imageURL}"
                                                        />
                                                </div>
                                                <div class="container_name_date_post">
                                                    <a href="loadProfile?email=${pendingDTO.emailPost}">
                                                        <p class="username">${pendingDTO.name}</p>
                                                    </a>
                                                    <p class="date_posted">${pendingDTO.dateCreated}</p>
                                                </div>
                                            </div>
                                            <div class="post_info">
                                                <a href="loadContentPendingPost?postID=${pendingDTO.postID}">
                                                    <h1 class="title_post">
                                                        ${pendingDTO.title}
                                                    </h1>
                                                </a>
                                                <p class="content"></p>
                                                <div class="hashtag">
                                                    <c:forEach var="tag" items="${pendingDTO.tag}">

                                                        <p><span class="hash"></span>#${tag}</p>

                                                    </c:forEach>
                                                </div>
                                            </div> 
                                        </div>

                                    </div>

                                </c:forEach>
                            </c:if>
                            <c:if test="${empty pendingList}">
                                <div class="no-pending">
                                    <h1>No pending post</h1>
                                </div>
                            </c:if>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->

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
</body>
</html>
