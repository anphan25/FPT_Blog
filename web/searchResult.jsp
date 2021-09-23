<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link rel="stylesheet" href="./styles/searchResult.css" />
    <title>Search Result</title>
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
            <form action="searchTitle">
              <input
                placeholder="Search..."
                name="titleValue"
                value="${param.titleValue}"
                autocomplete="off"
              />
            </form>
            <div class="container_icon">
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
                <a>Sign Out</a>
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

    <!-- container -->
    <!-- container -->
    <!-- container -->
    <div class="container">
      <div class="left-menu">
        <div class="navigation_left">
          <div class="sidebar_navigation">
            <h2 class="title_navigation">Menu</h2>
            <a href="/">
              <div class="container_item">
                <img src="./images/house_icon.svg" />
                <p>Home</p>
              </div>
            </a>
            <a href="">
              <div class="container_item">
                <img src="./images/category_icon.svg" />
                <p>Create Category</p>
              </div>
            </a>
            <a href="">
              <div class="container_item">
                <img src="./images/list_icon.svg" />
                <p>User List</p>
              </div>
            </a>
          </div>
        </div>
      </div>
      <div class="container-item">
        <h1 class="search-result-title">Search Result</h1>
        <div class="container_posts">
          <c:set
            var="searchTitleList"
            value="${requestScope.SEARCHLIST_TITLE}"
          />
          <c:if test="${not empty searchTitleList}">
            <c:forEach var="searchTitleDTO" items="${searchTitleList}">
              <div class="post">
                <a href="/contentPage.html">
                  <div class="container_info_post">
                    <div class="user_info">
                      <div class="container_avatar">
                        <img
                          src="https://scontent.fvca1-3.fna.fbcdn.net/v/t1.6435-9/240940699_1592346694443253_6861475202472920742_n.jpg?_nc_cat=111&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=QShWiSLfdbcAX8jkGI7&_nc_ht=scontent.fvca1-3.fna&oh=b32b69a2f8495d0493bef7959757cd3a&oe=61603430"
                        />
                      </div>
                      <div class="container_name_date_post">
                        <p class="username">${searchTitleDTO.namePost}</p>
                        <p class="date_posted">
                          ${searchTitleDTO.approvedDate}
                        </p>
                      </div>
                    </div>
                    <div class="post_info">
                      <c:url var="loadContentLink" value="loadPostContent">
                        <c:param name="postId" value="${blogDTO.ID}" />
                      </c:url>
                      <a href="${loadContentLink}">
                        <h1 class="title_post">${searchTitleDTO.title}</h1>
                      </a>
                      <div class="hashtag">
                        <p><span class="hash">#</span>${searchTitleDTO.tag}</p>
                      </div>
                      <div class="statistic">
                        <div class="reaction_and_comments">
                          <div>
                            <img src="./images/vote_icon.svg" />
                            <p>
                              ${searchTitleDTO.likes}
                              <span class="text_comments_votes">Likes</span>
                            </p>
                          </div>
                          <div>
                            <img src="./images/comment_icon.svg" />
                            <p>
                              ${searchTitleDTO.comments}
                              <span class="text_comments_votes">Comments</span>
                            </p>
                          </div>
                          <div>
                            <img src="./images/award2.svg" />
                            <p>
                              ${searchTitleDTO.awardID}
                              <span class="text_comments_votes">Awards</span>
                            </p>
                          </div>
                        </div>
                        <div class="time_and_save">
                          <div>
                            <button>Save</button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </a>
              </div>
            </c:forEach>
          </c:if>
          <c:if test="${empty searchTitleList}">
            <div class="no-result">
              <h1>No result matches</h1>
            </div>
          </c:if>
        </div>
      </div>
    </div>

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
    </script>
  </body>
</html>
