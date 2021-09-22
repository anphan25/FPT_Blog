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
            <button><a href="loginPage">Login</a></button>
          </div>
          <div class="container_button_register">
            <button><a href="loginPage">Create account</a></button>
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
              <button>
                <img src="./images/gmail_icon.png" />
                <p>Log in With Gmail</p>
              </button>
            </div>
          </div>
          <p class="or">Or</p>
          <form action="login" method="POST">
            <div class="field_input">
              <p>Email</p>
              <input
                type="email"
                class="form-control"
                id="exampleInputEmail1"
                name="txtEmail"
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
              />
            </div>
            <button type="submit" class="btn btn-primary" name="btAction">Login</button>
          </form>
          <p class="text_forgot_password">Tui quên mật khẩu</p>
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
        <img src="/images/forem_icon.svg" />
      </div>
    </footer>

    <script>
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
    </script>
  </body>
</html>