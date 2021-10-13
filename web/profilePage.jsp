<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@page
contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="profile" value="${requestScope.PROFILE_INFORMATION}" />
<c:set var="bloglist" value="${requestScope.PROFILE_BLOG}" />
<c:set var="login" value="${sessionScope.ACCOUNT}" />
<c:set var="loginStatus" value="${sessionScope.LOGIN}" />
<c:set var="currentUser" value="${sessionScope.CURRENT_USER}" />
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
    <title>Profile | FPT Blog</title>
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
                autocomplete="off"
              />
            </form>
            <div class="container_icon">
              <i class="fas fa-search"></i>
            </div>
          </div>
        </div>

        <c:if test="${loginStatus == 'logined'}">
          <div class="container_right">
            <c:if test="${currentUser.role == 'S'}">
              <div class="container_button_register">
                <a href="createPostPage"><button>Create Post</button></a>
              </div>
            </c:if>
            <c:if test="${currentUser.role == 'M'}">
              <div class="container_button_register">
                <a href="loadPendingPosts?postStatus=WFA"
                  ><button>Pending Post</button></a
                >
              </div>
            </c:if>
            <c:if test="${currentUser.role == 'A'}">
              <div class="container_button_register">
                <a href="createCategoryPage"
                  ><button>Create Category</button></a
                >
              </div>
            </c:if>
            <div class="icon_notification_container">
              <img src="./images/notification_icon.svg" />
            </div>
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
        </c:if>
        <c:if test="${loginStatus != 'logined'}">
          <div class="container_right">
            <div class="container_button_login">
              <button><a href="firstLoginPage">Login</a></button>
            </div>
            <div class="container_button_register">
              <button><a href="registerPage">Create account</a></button>
            </div>
          </div>
        </c:if>
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
                <button>
                  <a href="/login.html">TÃ¡ÂºÂ¡o tÃÂ i khoÃ¡ÂºÂ£n</a>
                </button>
              </div>
              <div class="container_button_login">
                <button><a href="/login.html">ÃÂÃÆng nhÃ¡ÂºÂ­p</a></button>
              </div>
            </div>
          </div>
          <div class="sidebar_navigation">
            <h2 class="title_navigation">Menu</h2>
            <div class="container_item">
              <img src="./images/house_icon.svg" />
              <p>Trang chÃ¡Â»Â§</p>
            </div>
            <div class="container_item">
              <img src="./images/hand_shake_icon.svg" />
              <p>ÃÂÃÆng nhÃ¡ÂºÂ­p</p>
            </div>
          </div>
          <div class="sidebar_navigation">
            <h2 class="title_navigation">Tags phÃ¡Â»â¢ biÃ¡ÂºÂ¿n</h2>
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
          <img src="${profile.imageURL}" alt="" />
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
              <c:if test="${not empty profile.awards}">
                <c:forEach var="award" items="${profile.awards}">
                  <c:if test="${award == 1}">
                    <div class="award-item">
                      <img
                        class="awarded-icon"
                        src="./images/contribution-award.svg"
                        alt=""
                      />
                      <p>Great Contributor</p>
                    </div>
                  </c:if>
                  <c:if test="${award == 2}">
                    <div class="award-item">
                      <img
                        class="awarded-icon"
                        src="./images/heart-award.png"
                        alt=""
                      />
                      <p>Like Collector</p>
                    </div>
                  </c:if>
                </c:forEach>
              </c:if>
              <c:if test="${empty profile.awards}">
                <p>No award</p>
              </c:if>
            </div>
          </div>
          <div class="posted-posts">
            <c:if test="${not empty bloglist}">
              <c:forEach var="blog" items="${bloglist}">
                <div class="posted-post">
                  <div class="time">${blog.approvedDate}</div>
                  <div class="post-items">
                    <c:url var="loadContentLink" value="loadPostContent">
                      <c:param name="postId" value="${blog.ID}" />
                    </c:url>
                    <a href="${loadContentLink}">
                      <p class="post-title">${blog.title}</p>
                    </a>
                    <div class="post-tags">
                      <c:forEach var="tag" items="${blog.tag}">
                        <c:url var="searchByTagLink" value="searchByTag">
                          <c:param name="tag" value="${tag}" />
                        </c:url>
                        <a href="${searchByTagLink}">
                          <p class="post-tag">#${tag}</p>
                        </a>
                      </c:forEach>
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
                      </div>
                    </div>
                  </div>
                </div>
              </c:forEach>
            </c:if>
            <c:if test="${empty bloglist}">
              <div class="user-nopost">
                <h1>No blog was posted</h1>
              </div>
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
