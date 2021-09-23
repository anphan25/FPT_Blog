<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="profile" value="${requestScope.PROFILE_INFORMATION}" />
<c:set var="bloglist" value ="${requestScope.PROFILE_BLOG}" />
<c:set var="login" value="${sessionScope.ACCOUNT}" />


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
      rel="stylesheet"
    />
    <script
      src="https://kit.fontawesome.com/1b1fb57155.js"
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="./styles/profilePageStyle.css" />
    <title>Profile</title>
  </head>
  <body>
    <!-- header  -->
    <!-- header  -->
    <!-- header  -->
    <header>
      <div class="container_header">
        <div class="container_left">
          <div class="toggle_sidebar" onclick="toggleSidebarPhone()">
            <img src="/images/toggle_sidebar_icon.svg" />
          </div>
          <div class="container_logo">
            <a href="/">
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
              <div class="dropdown_category_item">
                <a href="">IT & Software</a>
              </div>
              <div class="dropdown_category_item"><a href="">Marketing</a></div>
              <div class="dropdown_category_item"><a href="">Business</a></div>
              <div class="dropdown_category_item"><a href="">Design</a></div>
              <div class="dropdown_category_item">
                <a href="">Finance & Accounting</a>
              </div>
            </div>
          </div>
          <div class="container_searchBar">
            <input placeholder="Search..." />
            <div class="container_icon">
              <i class="fas fa-search"></i>
            </div>
          </div>
        </div>

        <div class="container_right">
          <div class="container_button_register">
            <button><a href="">Create Post</a></button>
          </div>
          <div class="icon_notification_container">
            <img src="./images/notification_icon.svg" />
          </div>
          <div class="dropdown">
            <div class="dropbtn">
              <img
                <c:if test="${empty login}">
                src="https://imgix.bustle.com/nylon/18381941/origin.png?w=1200&h=1000&fit=crop&crop=faces&auto=format%2Ccompress"
                </c:if>
                <c:if test="${not empty login}">
                src="${login.imageURL}"
                </c:if>
              />
            </div>
            <c:if test="${not empty login}">
            <div class="dropdown-content">
              <div class="item-top">
                <a
                  ><h2>${login.name}</h2>
                  <p>@${login.campus}</p></a
                >
              </div>
              <div style="padding: 0.5rem 0">
                <div class="item">
                  <a href="LoadProfileServlet?email=${login.email}"><p>Profile</p></a>
                </div>
                <div class="item">
                  <a><p>Create Post</p></a>
                </div>
              </div>
              <div class="item-bottom">
                <a>Sign Out</a>
              </div>
            </c:if>
            </div>
          </div>

          <!-- <div class="container_button_login">
            <button><a href="/login.html">Đăng nhập</a></button>
          </div>
          <div class="container_button_register">
            <button><a href="/login.html">Tạo tài khoản</a></button>
          </div> -->
        </div>
      </div>
    </header>

    <!-- sidebar_phone  -->
    <!-- sidebar_phone  -->
    <!-- sidebar_phone  -->
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
                <button><a href="/login.html">Tạo tài khoản</a></button>
              </div>
              <div class="container_button_login">
                <button><a href="/login.html">Đăng nhập</a></button>
              </div>
            </div>
          </div>
          <div class="sidebar_navigation">
            <h2 class="title_navigation">Menu</h2>
            <div class="container_item">
              <img src="./images/house_icon.svg" />
              <p>Trang chủ</p>
            </div>
            <div class="container_item">
              <img src="./images/hand_shake_icon.svg" />
              <p>Đăng nhập</p>
            </div>
          </div>
          <div class="sidebar_navigation">
            <h2 class="title_navigation">Tags phổ biến</h2>
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

    <!-- cover -->
    <!-- cover -->
    <div class="cover"></div>
    <!-- container -->
    <!-- container -->
    <!-- container -->
    <div class="container">
      <div class="info">
        <div class="profile-avt">
          <img
            src="${profile.imageURL}"
            alt=""
          />
        </div>
        <div class="info-content">
          <p class="info-name">${profile.name}</p>
        </div>
      </div>
      <div class="main-content">
        <div class="row">
          <div class="user-info">
            <div class="user-info-item">
              <h4>Gender:</h4>
              <p>${profile.gender}</p>
            </div>
            <div class="user-info-item">
              <h4>Campus:</h4>
              <p>${profile.campus}</p>
            </div>
            <div class="user-info-item">
              <h4>Email:</h4>
              <p>${profile.email}</p>
            </div>
            <div class="user-info-item">
              <h4>Role:</h4>
              <p>${profile.role}</p>
            </div>
            <div class="user-info-item">
              <h4>Award:</h4>
              <i class="fas fa-trophy"></i> <span>${profile.awards}</span>
            </div>
          </div>
          <div class="posted-posts">
              <c:if test = "${not empty bloglist}">
                  <c:forEach var = "blog" items = "${bloglist}" >
            <div class="posted-post">
              <div class="time">${blog.approvedDate}</div>
              <div class="post-items">
                <p class="post-title">${blog.title}</p>
                <div class="post-tags">
                  <p class="post-tag"><span class="hash">#</span>${blog.tag}</p>
                  <!--
                  <p class="post-tag"><span class="hash">#</span>vue</p>
                  <p class="post-tag"><span class="hash">#</span>angular</p>
                  <p class="post-tag"><span class="hash">#</span>javascript</p>
                  -->
                </div>
                <div class="statistic">
                  <div class="reaction_and_comments">
                    <div>
                      <img src="./images/vote_icon.svg" />
                      <p>
                        ${blog.likes}
                        <span class="text_comments_votes">Likes</span>
                      </p>
                    </div>
                    <div>
                      <img src="./images/comment_icon.svg" />
                      <p>
                        ${blog.comments}
                        <span class="text_comments_votes">Comments</span>
                      </p>
                    </div>
                    <div>
                      <img src="./images/award2.svg" />
                      <p>
                        ${blog.awardID}
                        <span class="text_comments_votes">Awards</span>
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
                  </c:forEach>
              </c:if>
          </div>
        </div>
      </div>
    </div>

    <!-- footer  -->
    <!-- footer  -->
    <!-- footer  -->
    <footer>
      <div class="container_footer">
        <p>
          <span class="text_footer_strong">DEV Community</span> ? A constructive
          and inclusive social network for software developers. With you every
          step of your journey.
        </p>
        <div style="margin: 0.25rem 0"></div>
        <p>
          Built on <span class="text_footer_strong">Forem</span> ? the
          <span class="text_footer_strong">open source</span> software that
          powers DEV and other inclusive communities
        </p>
      </div>
      <div class="text_footer_container">
        <p class="text_footer">
          Made with
          <i class="fa fa-heart" style="color: rgb(255, 70, 50)"></i> by
          <span class="text_footer_strong">�n, An, ?an, Nam, Ph??ng</span> �
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
