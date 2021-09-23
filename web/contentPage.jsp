<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <link rel="stylesheet" href="./styles/contentPageStyle.css" />
        <title>Content</title>
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
                        <form action="searchTitle">
                            <input placeholder="Search..." name="titleValue" autocomplete="off"/>
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

        <!-- container   -->
        <!-- container   -->
        <!-- container   -->
        <div class="container">
            <div class="row">
                <div class="interact">
                    <a href="/">
                        <div class="interact-item">
                            <div class="icon like-icon">
                                <img src="./images/vote_icon.svg" alt="" />
                            </div>
                            <p>200</p>
                        </div>
                    </a>
                    <div class="interact-item">
                        <div class="icon cmt-icon">
                            <img src="./images/comment_icon.svg" alt="" />
                        </div>
                        <p>69</p>
                    </div>
                    <div class="interact-item">
                        <div class="icon award-icon" title="Only mentor can give award !!!">
                            <img src="./images/award2.svg" alt="" />
                        </div>
                        <p>2</p>
                    </div>
                </div>
                <div class="main-content">
                    <div class="main-content-up">
                        <div class="title">
                            <p class="title-text">Welcome to Blog</p>
                            <div class="tag">
                                <p>#Javascript</p>
                                <p>HTML</p>
                                <p>CSS</p>
                            </div>
                            <div class="owner">
                                <div class="avt">
                                    <img
                                        class="avt-img"
                                        src="https://img.hoidap247.com/picture/question/20200508/large_1588936738888.jpg"
                                        alt=""
                                        />
                                </div>
                                <form action="">
                                    <div class="name">
                                        <a href="/profilePage.html">ThiÃªn Ãn</a>
                                    </div>
                                </form>
                                <div class="time">11/09/2021</div>
                            </div>
                        </div>
                        <div class="content-img-text">
                            <img
                                src="https://nordiccoder.com/app/uploads/2019/12/50-javascript.jpg"
                                alt=""
                                />
                            <p>
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Aut
                                consectetur repudiandae numquam illo tempora enim error? Saepe
                                odit aut accusantium pariatur tempore quod, impedit eius, labore
                                accusamus porro nisi aspernatur?Lorem ipsum dolor sit amet
                                consectetur adipisicing elit. Aut consectetur repudiandae
                                numquam illo tempora enim error? Saepe odit aut accusantium
                                pariatur tempore quod, impedit eius, labore accusamus porro nisi
                                aspernatur?Lorem ipsum dolor sit amet consectetur adipisicing
                                elit. Aut consectetur repudiandae numquam illo tempora enim
                                error? Saepe odit aut accusantium pariatur tempore quod, impedit
                                eius, labore accusamus porro nisi aspernatur?Lorem ipsum dolor
                                sit amet consectetur adipisicing elit. Aut consectetur
                                repudiandae numquam illo tempora enim error? Saepe odit aut
                                accusantium pariatur tempore quod, impedit eius, labore
                                accusamus porro nisi aspernatur?Lorem ipsum dolor sit amet
                                consectetur adipisicing elit. Aut consectetur repudiandae
                                numquam illo tempora enim error? Saepe odit aut accusantium
                                pariatur tempore quod, impedit eius, labore accusamus porro nisi
                                aspernatur?Lorem ipsum dolor sit amet consectetur adipisicing
                                elit. Aut consectetur repudiandae numquam illo tempora enim
                                error? Saepe odit aut accusantium pariatur tempore quod, impedit
                                eius, labore accusamus porro nisi aspernatur? Lorem ipsum dolor,
                                sit amet consectetur adipisicing elit. Nulla blanditiis odio
                                quasi et fugiat, quaerat impedit qui sequi voluptates rerum
                                distinctio in modi explicabo. Eveniet odio labore debitis
                                repellat officia. Lorem ipsum dolor sit amet consectetur
                                adipisicing elit. Animi minus in totam debitis dignissimos porro
                                nesciunt voluptas dolorem architecto, pariatur voluptatem beatae
                                autem suscipit nostrum temporibus amet quasi recusandae veniam?
                                Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quos
                                fugit, molestias tempore, aut doloremque, voluptatem sit dicta
                                eligendi beatae quibusdam dolorem atque perferendis similique
                                magni assumenda deserunt necessitatibus cupiditate libero.
                            </p>
                        </div>
                    </div>
                    <div class="comment">
                        <p class="comment-title">Discussion (69)</p>
                        <div class="current-user-comment">
                            <div class="user-avt">
                                <img
                                    class="avt-img"
                                    src="https://images.hoidap247.com/picture/question/20200508/large_1588936738888.jpg"
                                    alt=""
                                    />
                            </div>
                            <div class="user-comment-item">
                                <form action="">
                                    <textarea
                                        name="cmt"
                                        id="textarea-cmt"
                                        cols="30"
                                        rows="10"
                                        placeholder="Add to the discussion"
                                        ></textarea>
                                    <button
                                        class="submit-btn hidden"
                                        disabled="true"
                                        type="submit"
                                        >
                                        Submit
                                    </button>
                                </form>
                            </div>
                        </div>
                        <div class="others-comments">
                            <div class="user-avt">
                                <img
                                    class="avt-img"
                                    src="https://images.wattpad.com/5d48fc0aa923db78e84ce866b506a5380b816699/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f776174747061642d6d656469612d736572766963652f53746f7279496d6167652f4f7a744367615f4a6c4759546d673d3d2d3231342e3134643764326263626130633136663737323038363831333430302e6a7067"
                                    alt=""
                                    />
                            </div>
                            <div class="comment-item">
                                <div class="comment-info">
                                    <div class="name">Gia An</div>
                                    <div class="comment-time">16:27 11/9/2021</div>
                                </div>
                                <div class="comment-content">
                                    <p>Tháº­t thuyá»t vá»i</p>
                                </div>
                            </div>
                            <form action="">
                                <div class="edit-delete">
                                    <button><i class="fas fa-pen"></i> Edit</button>
                                    <button><i class="fas fa-trash-alt"></i> Delete</button>
                                </div>
                            </form>
                        </div>
                        <div class="others-comments">
                            <div class="user-avt">
                                <img
                                    class="avt-img"
                                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTA2JwfW8wriS7T_mm4tr78QyH3cmOOe57IPmy3ZKjjyubcIu4QL6l8N5wHhCKYGXJPwPI&usqp=CAU"
                                    alt=""
                                    />
                            </div>
                            <div class="comment-item">
                                <div class="comment-info">
                                    <div class="name">Háº£i Nam</div>
                                    <div class="comment-time">16:27 11/9/2021</div>
                                </div>
                                <div class="comment-content">
                                    <p>Äá»nh cá»§a chÃ³p</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="left-container">
                    <div class="left-container-title">
                        <h3>Nhá»¯ng bÃ i viáº¿t ná»i báº­t</h3>
                    </div>
                    <div class="left-container-content">
                        <div class="left-container-item">
                            <h6>I Createdd my VSCode theme</h6>
                            <p class="title-content">#Javascript #HTML #CSS</p>
                        </div>
                    </div>
                    <div class="left-container-content">
                        <div class="left-container-item">
                            <h6>Sharing my experience in learning</h6>
                            <p class="title-content">#Javascript #HTML #CSS</p>
                        </div>
                    </div>
                    <div class="left-container-content">
                        <div class="left-container-item">
                            <h6>Tips for HTML/CSS</h6>
                            <p class="title-content">#Javascript #HTML #CSS</p>
                        </div>
                    </div>
                    <div class="left-container-content">
                        <div class="left-container-item">
                            <h6>How to learn Java</h6>
                            <p class="title-content">#Javascript #HTML #CSS</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal -->
        <!-- modal -->
        <!-- modal -->
        <div class="login-modal hidden">
            <div class="login-modal-title">
                <h2>Log in to continue</h2>
                <div class="close-btn-modal-div">
                    <i class="fas fa-times close-modal-btn"></i>
                </div>
            </div>
            <div class="login-modal-content">
                <div class="login-modal-logo">
                    <img
                        src="https://i.chungta.vn/2017/12/22/LogoFPT-2017-copy-3042-1513928399.jpg"
                        alt=""
                        />
                </div>
                <div class="login-modal-button">
                    <a class="login-link" href="/login.html">Log in</a>
                    <a class="register-link" href="/login.html">Create new account</a>
                </div>
            </div>
            <div class="login-modal-footer">
                We strive for transparency and don't collect excess data.
            </div>
        </div>
        <div class="overlay hidden"></div>
        <!-- footer -->
        <!-- footer -->
        <!-- footer -->
        <footer>
            <div class="container_footer">
                <p>
                    <span class="text_footer_strong">DEV Community</span> â A constructive
                    and inclusive social network for software developers. With you every
                    step of your journey.
                </p>
                <div style="margin: 0.25rem 0"></div>
                <p>
                    Built on <span class="text_footer_strong">Forem</span> â the
                    <span class="text_footer_strong">open source</span> software that
                    powers DEV and other inclusive communities
                </p>
            </div>
            <div class="text_footer_container">
                <p class="text_footer">
                    Made with
                    <i class="fa fa-heart" style="color: rgb(255, 70, 50)"></i> by
                    <span class="text_footer_strong">Ãn, An, Äan, Nam, PhÆ°Æ¡ng</span> Â©
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
        <script src="./js/contentPage.js"></script>
    </body>
</html>