<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib
    uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <script src="https://apis.google.com/js/platform.js" async defer></script>
            <!-- meta name scope profile email for google api -->
            <meta
                name="google-signin-client_id"
                content="229851668671-rehm8b9h7e190bhmtvdmf24p19g39p3d.apps.googleusercontent.com"
                />
            <meta name="google-signin-scope" content="profile email" />
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> <!-- jquery -->
            <link rel="stylesheet" href="./styles/login.css" />
            <title>Login | FPT Blog</title>
        </head>

        <body>
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
                        <!--                    <div class="dropdown_category">
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
                                            ><h2>BÃ¡nh bÃ¨o 2k1</h2>
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
                            <button><a href="firstLoginPage">Login</a></button>
                        </div>
                        <div class="container_button_register">
                            <button><a href="registerPage">Create account</a></button>
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
                        <div class="login_with_third_party">
                            <div class="item">
                                <button class="login-gmail-btn">
                                    <img src="./images/gmail_icon.png" />
                                    <p>Log in With Gmail</p>
                                </button>
                            </div>
                        </div>
                        <div class="g-signin2" data-onsuccess="onSignIn"></div>
                        <p class="or">Or</p>
                        <form action="login" method="POST">
                            <c:if test="${requestScope.BAN == 'banned'}">
                                <h3>
                                    <font color="red"> Your Account was banned by Admin !!! </font>
                                </h3>
                            </c:if>
                            <c:if test="${requestScope.FPT == 'NOTFPT'}">
                                <h3>
                                    <font color="red">
                                    We only accept FPT gmail !!!
                                    </font>
                                </h3>
                            </c:if>

                            <c:if test="${requestScope.BAN != 'banned'}">
                                <h3>
                                    <font color="red">
                                    Incorrect login information. Try again.
                                    </font>
                                </h3>
                            </c:if>

                            <div class="field_input">
                                <p>Email</p>
                                <input
                                    type="email"
                                    class="form-control"
                                    id="exampleInputEmail1"
                                    name="txtEmail"
                                    value="${param.txtEmail}"
                                    aria-describedby="emailHelp"
                                    />
                            </div>
                            <div class="field_input">
                                <p>Password</p>
                                <input
                                    type="password"
                                    class="form-control"
                                    name="txtPassword"
                                    id="exampleInputPassword1"
                                    value="${param.txtPassword}"
                                    />
                                <i class="fas fa-eye"></i>
                            </div>
                            <button type="submit" class="btn btn-primary" name="btAction">
                                Login
                            </button>
                        </form>
                        <!--                    <p class="text_forgot_password">Tui quÃªn máº­t kháº©u</p>-->
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

            <div class="ban-reason-modal ">
                <img src="./images/ban-icon.png" alt="">
                <h2>reason here</h2>
                <button class="OKbtn-ban-reason">Ok</button>
            </div>
            <div class="ban-reason-overlay " ></div>

            <script>
                const passwordInput = document.querySelector("#exampleInputPassword1");
                const eye = document.querySelector(".fa-eye");

                let x = true;

                eye.addEventListener("click", () => {
                    if (x) {
                        passwordInput.type = "text";
                        x = false;
                    } else {
                        passwordInput.type = "password";
                        x = true;
                    }
                });

                const ggLogin = document.querySelector(".g-signin2");
                document
                        .querySelector(".login_with_third_party")
                        .addEventListener("click", () => {
                            ggLogin.firstChild.click();
                        });
                // history.pushState(null, document.title, location.href);
                // history.back();
                // history.forward();
                // window.onpopstate = function () {
                //   history.go(1);
                // };
                history.pushState(null, document.title, location.href);
                window.addEventListener("popstate", function (event) {
                    history.pushState(null, document.title, location.href);
                });
                function onSignIn(googleUser)
                {
                    var id_token = googleUser.getAuthResponse().id_token; //gimme ur ass
                    if (id_token !== null) {
                        //well đây là th đăng nhập thành công và bên hờ tờ mờ lờ lấy được idtoken
                        SignOut();
                        //window.location.href = "LoginGoogleServlet?token=" + id_token; địt mẹ http get anh em tao làm http post. Sau khi đã tu luyện AJAX 3 ngày 3 đêm
                        $('<form action="' + 'loginGoogle"' + 'method="POST">' +
                                '<input type="text" name="token" value="' + id_token + '" />' + //upgrade complete
                                '</form>').appendTo('body').submit();
                        //ez FAP dùng GET bố m dùng POST
                    }
                    //console.log(id_token);
                    //this code below here is for the situation google dead.
                }

                function SignOut()
                {
                    //well if you have to copied just exactly fap.fpt.edu then go write some shit down here
                    var gaObject = gapi.auth2.getAuthInstance();
                    gaObject.signOut().then(function () {});
                }

                const banReasonModal = document.querySelector(
                        ".ban-reason-modal"
                        );
                const banReasonOverlay = document.querySelector(
                        ".ban-reason-overlay"
                        );

                document
                        .querySelector(".OKbtn-ban-reason")
                        .addEventListener("click", () => {
                            banReasonModal.classList.toggle("hidden");
                            banReasonOverlay.classList.toggle("hidden");
                        });

                banReasonOverlay.addEventListener("click", () => {
                    banReasonModal.classList.toggle("hidden");
                    banReasonOverlay.classList.toggle("hidden");
                });
            </script>
        </body>
    </html>
