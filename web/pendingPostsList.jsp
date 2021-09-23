<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                        <form action="searchTitle">
                            <input placeholder="Search..." name="titleValue" autocomplete="off"/>
                        </form>
                        <div class="container_icon">
                            <i class="fas fa-search"></i>
                        </div>
                    </div>
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
                        <button><a href="/login.html">Pending Post</a>
                            <div class="num-post"><span>3</span>
                        </button>
                    </div>
                    <div class="icon_notification_container">
                        <img src="./images/notification_icon.svg" />
                    </div>
                    <div class="dropdown">
                        <div class="dropbtn">
                            <img
                                src="https://scontent.fvca1-3.fna.fbcdn.net/v/t1.6435-9/240940699_1592346694443253_6861475202472920742_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=JLhcw5FJgPIAX8kuBD0&_nc_ht=scontent.fvca1-3.fna&oh=28779448f7468d3c01d8f2febd7e2c06&oe=61681D30"
                                />
                        </div>
                        <div class="dropdown-content">
                            <div class="item-top">
                                <a
                                    ><h2>BÃ¡nh bÃ¨o 2k1</h2>
                                    <p>@giaandeptrai123</p></a
                                >
                            </div>
                            <div style="padding: 0.5rem 0">
                                <div class="item">
                                    <a href="profilePage.html"><p>Profile</p></a>
                                </div>
                                <div class="item">
                                    <a><p>Create Post</p></a>
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
                            <a href="/">
                                <div class="container_item">
                                    <img src="./images/house_icon.svg" />
                                    <p>Home</p>
                                </div>
                            </a>
                            <a href="/pendingPostsList.html">
                                <div class="container_item">
                                    <img src="./images/list_icon.svg" />
                                    <p>Pending Posts</p>
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
                        <div class="pending_posts">
                            <div class="column">
                                <div class="post">
                                    <a href="/postWaitingApproval.html">
                                        <div class="container_info_post">
                                            <div class="post_info">
                                                <h1 class="title_post">
                                                    Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                                    Optio, numquam.
                                                </h1>
                                                <p class="content">
                                                    â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸
                                                    â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸
                                                    â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸
                                                    â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸
                                                    â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸
                                                    â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃªðâ­ï¸ â­ï¸ðSao kÃª
                                                </p>
                                                <div class="hashtag">
                                                    <p><span class="hash">#</span>react</p>
                                                    <p><span class="hash">#</span>vue</p>
                                                    <p><span class="hash">#</span>angular</p>
                                                    <p><span class="hash">#</span>javascript</p>
                                                </div>
                                            </div>
                                            <div class="user_info">
                                                <div class="container_avatar">
                                                    <img
                                                        src="https://scontent.fvca1-3.fna.fbcdn.net/v/t1.6435-9/240940699_1592346694443253_6861475202472920742_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=QShWiSLfdbcAX8jkGI7&_nc_ht=scontent.fvca1-3.fna&oh=b32b69a2f8495d0493bef7959757cd3a&oe=61603430"
                                                        />
                                                </div>
                                                <div class="container_name_date_post">
                                                    <p class="username">BÃ¡nh bÃ¨o</p>
                                                    <p class="date_posted">Sep 11 '21</p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="post">
                                    <a href="/postWaitingApproval.html">
                                        <div class="container_info_post">
                                            <div class="post_info">
                                                <h1 class="title_post">
                                                    Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                                    Optio, numquam.
                                                </h1>
                                                <p class="content"></p>
                                                <div class="hashtag">
                                                    <p><span class="hash">#</span>react</p>
                                                    <p><span class="hash">#</span>vue</p>
                                                    <p><span class="hash">#</span>angular</p>
                                                    <p><span class="hash">#</span>javascript</p>
                                                </div>
                                            </div>
                                            <div class="user_info">
                                                <div class="container_avatar">
                                                    <img
                                                        src="https://scontent.fvca1-3.fna.fbcdn.net/v/t1.6435-9/240940699_1592346694443253_6861475202472920742_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=QShWiSLfdbcAX8jkGI7&_nc_ht=scontent.fvca1-3.fna&oh=b32b69a2f8495d0493bef7959757cd3a&oe=61603430"
                                                        />
                                                </div>
                                                <div class="container_name_date_post">
                                                    <p class="username">BÃ¡nh bÃ¨o</p>
                                                    <p class="date_posted">Sep 11 '21</p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <div class="column">
                                <div class="post">
                                    <a href="/postWaitingApproval.html">
                                        <div class="container_info_post">
                                            <div class="post_info">
                                                <h1 class="title_post">
                                                    Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                                    Optio, numquam.
                                                </h1>
                                                <p class="content"></p>
                                                <div class="hashtag">
                                                    <p><span class="hash">#</span>react</p>
                                                    <p><span class="hash">#</span>vue</p>
                                                    <p><span class="hash">#</span>angular</p>
                                                    <p><span class="hash">#</span>javascript</p>
                                                </div>
                                            </div>
                                            <div class="user_info">
                                                <div class="container_avatar">
                                                    <img
                                                        src="https://scontent.fvca1-3.fna.fbcdn.net/v/t1.6435-9/240940699_1592346694443253_6861475202472920742_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=QShWiSLfdbcAX8jkGI7&_nc_ht=scontent.fvca1-3.fna&oh=b32b69a2f8495d0493bef7959757cd3a&oe=61603430"
                                                        />
                                                </div>
                                                <div class="container_name_date_post">
                                                    <p class="username">BÃ¡nh bÃ¨o</p>
                                                    <p class="date_posted">Sep 11 '21</p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="post">
                                    <a href="/postWaitingApproval.html">
                                        <div class="container_info_post">
                                            <div class="post_info">
                                                <h1 class="title_post">
                                                    Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                                    Optio, numquam.
                                                </h1>
                                                <p class="content">
                                                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi laboriosam itaque, unde hic illum ipsa veniam, similique nesciunt, doloribus ab adipisci. Sunt animi officia, magni, laborum vitae velit quae minima debitis asperiores doloribus, laudantium quibusdam laboriosam ad neque dolorum quia.
                                                </p>
                                                <div class="hashtag">
                                                    <p><span class="hash">#</span>react</p>
                                                    <p><span class="hash">#</span>vue</p>
                                                    <p><span class="hash">#</span>angular</p>
                                                    <p><span class="hash">#</span>javascript</p>
                                                </div>
                                            </div>
                                            <div class="user_info">
                                                <div class="container_avatar">
                                                    <img
                                                        src="https://scontent.fvca1-3.fna.fbcdn.net/v/t1.6435-9/240940699_1592346694443253_6861475202472920742_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=QShWiSLfdbcAX8jkGI7&_nc_ht=scontent.fvca1-3.fna&oh=b32b69a2f8495d0493bef7959757cd3a&oe=61603430"
                                                        />
                                                </div>
                                                <div class="container_name_date_post">
                                                    <p class="username">BÃ¡nh bÃ¨o</p>
                                                    <p class="date_posted">Sep 11 '21</p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
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
