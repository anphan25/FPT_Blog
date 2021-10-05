<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="swp.accountError.AccountError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="gmail" value="${requestScope.GMAIL_REGISTER}" />

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
      src="https://kit.fontawesome.com/03ade0a214.js"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.0/sweetalert.min.js"></script>
    <link rel="stylesheet" href="./styles/register.css" />
    <title>Đăng kí | FPT Blog</title>
  </head>
  <body>
    <header>
      <div class="container_header">
        <div class="container_left">
          <div class="container_logo">
            <a href="loadBlogs">
              <img
                src="https://i.chungta.vn/2017/12/22/LogoFPT-2017-copy-3042-1513928399.jpg"
              />
            </a>
          </div>
<!--          <div class="dropdown_category">
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
          </div>-->
        </div>

        <div class="container_right">
          <!-- <div class="container_button_register">
            <button><a href="/login.html">Create Post</a></button>
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
                  ><h2>Bánh bèo 2k1</h2>
                  <p>@giaandeptrai123</p></a
                >
              </div>
              <div style="padding: 0.5rem 0">
                <div class="item">
                  <a><p>Profile</p></a>
                </div>
                <div class="item">
                  <a><p>Create Post</p></a>
                </div>
              </div>
              <div class="item-bottom">
                <a>Sign Out</a>
              </div>
            </div>
          </div> -->

          <div class="container_button_login">
            <button><a href="firstLoginPage">Log in</a></button>
          </div>
          <div class="container_button_register">
            <button><a href="registerPage">Create Account</a></button>
          </div>
        </div>
      </div>
    </header>

    <!-- Main -->
    <section class="main">
      <div class="layout_card">
        <div class="container_card">
          <h1>Welcome to FPT Community</h1>
          <p>
            <span class="brand_text">FPT Community</span> is a community of
            698,309 amazing developers
          </p>
          <!-- <div class="login_with_third_party">
            <div class="item">
              <button>
                <img src="./images/gmail_icon.png" />
                <p>Log in With Gmail</p>
              </button>
            </div>
          </div>
          <p class="or">Or</p> -->
          <%
              AccountError accountError = (AccountError)request.getAttribute("ACCOUNT_ERROR");
              if (accountError == null) {
                  accountError = new AccountError();
              }
          %>
          <form action="register" method="POST">
            <div class="field_input_responsive">
              <div class="field_input">
                <p>Name</p>
                <input
                  name="name"
                  type="name"
                  class="form-control"
                  id="name"
                  minlength="1"
                  <c:if test="${empty gmail}">
                  value="<%= accountError.getNameError()%>"
                  </c:if>
                  <c:if test="${not empty gmail}">
                  value="${gmail.name}"
                  </c:if>
                  aria-describedby="nameHelp"
                />
              </div>
              <div class="field_input">
                <p>Gender</p>
                <select name="gender" id="gender" class="form-control">
                  <option value="0">Female</option>
                  <option value="1">Male</option>
                </select>
              </div>
            </div>
            <div class="field_input_responsive">
              <div class="field_input_left">
                <p>Avatar</p>
                <div class="field_input_avatar_container">
                  <div class="avatar_preview">
                    <input
                      id="avatarURL"
                      type="hidden"
                      name="avatarURL"
                      <c:if test="${empty gmail}">
                      value="https://firebasestorage.googleapis.com/v0/b/udemy-vue-firebase-si.appspot.com/o/avatar-user%2F9f18e161-72c6-462b-9d66-6746360b38fa%2Favatar-default-icon.png?alt=media&token=959eb459-e9a8-4733-b8fa-c86c95d3950d"
                      </c:if>
                      <c:if test="${not empty gmail}">
                      value="${gmail.avatar}"
                      </c:if>
                    />
                    <img
                      <c:if test="${empty gmail}">
                      src="https://firebasestorage.googleapis.com/v0/b/udemy-vue-firebase-si.appspot.com/o/avatar-user%2F9f18e161-72c6-462b-9d66-6746360b38fa%2Favatar-default-icon.png?alt=media&token=959eb459-e9a8-4733-b8fa-c86c95d3950d"
                      </c:if>
                      <c:if test="${not empty gmail}">
                      src="${gmail.avatar}"
                      </c:if>
                      id="img-preview"
                    />
                  </div>
                  <div class="button_cover_image_container">
                    <div class="image-upload">
                      <label for="file-input-avatar">
                        Upload your avatar
                      </label>
                      <input
                        id="file-input-avatar"
                        type="file"
                        accept="image/png, image/jpeg"
                        style="display: none"
                      />
                    </div>
                  </div>
                </div>
              </div>
              <div class="field_input">
                <p>Campus</p>
                <select name="campus" id="campus" class="form-control" >
                  <option value="HCM">FU-Hồ Chí Minh</option>
                  <option value="Hà Nội">FU-Hoà Lạc</option>
                  <option value="Đà Nẵng">FU-Đà Nẵng</option>
                  <option value="Cần Thơ">FU-Cần Thơ</option>
                  <option value="Quy Nhơn">FU-Quy Nhơn</option>
                </select>
              </div>
            </div>
            <div class="field_input">
              <p>Email</p>
              <c:if test="${empty gmail}">
              <input
                name="email"
                type="email"
                class="form-control"
                id="email"
                aria-describedby="emailHelp"
              />
              </c:if>
              <c:if test="${not empty gmail}">
              <input
                name="showemail"
                type="email"
                class="form-control"
                id="email"
                aria-describedby="emailHelp"
                value="${gmail.email}"
                disabled
              />
              <input type="hidden" name="email" value="${gmail.email}"/> <!-- ez for ence -->
              </c:if>
              <p style="font-weight: 500; color: red"><%= accountError.getEmailError()%></p>
            </div>
            <div class="field_input">
              <p>Password</p>
              <input
                name="password"
                type="password"
                class="form-control"
                id="password"
                minlength="6"
              />
            </div>
            <div class="field_input">
              <p>Confirm Password</p>
              <input
                name="passwordRepeat"
                type="password"
                class="form-control"
                id="passwordRepeat"
                minlength="6"
              />
            </div>

            <button type="submit" class="btn btn-primary">
              Create Account
            </button>
          </form>
        </div>
      </div>
    </section>
    <!-- Footer -->

    <footer>
      <div class="container_footer">
        <p>
          <span class="text_footer_strong">FPT Community</span> – A constructive
          and inclusive social network for software developers. With you every
          step of your journey.
        </p>
        <div style="margin: 0.25rem 0"></div>
        <p>
          Built on <span class="text_footer_strong">Forem</span> — the
          <span class="text_footer_strong">open source</span> software that
          powers FPT and other inclusive communities
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-firestore.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-storage.js"></script>
    <script src="./js/registerPage.js"></script>
  </body>
</html>

