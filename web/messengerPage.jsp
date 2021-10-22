<%@page import="swp.post.PostDTO"%> <%@page contentType="text/html"
                                            pageEncoding="UTF-8"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
                                            prefix="c"%>
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

        <link rel="stylesheet" href="./styles/messenger.css" />
        <title>Chat | FPT Blog</title>
    </head>
    <body>
        <c:set var="currentUser" value="${sessionScope.CURRENT_USER}" />
        <c:set var="loginStatus" value="${sessionScope.LOGIN}" />
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
                                    <a href="searchByCategory?categoryId=${cateDTO.ID}"
                                       >${cateDTO.name}</a
                                    >
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="container_searchBar">
                        <form id="searchit" action="searchTitle">
                            <input
                                placeholder="Search..."
                                name="titleValue"
                                autocomplete="off"
                                />
                            <div class="container_icon" onclick="submit_form()">
                                <i class="fas fa-search"></i>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- <div class="container_right">
                            <div class="container_button_login">
                              <button><a href="firstloginpage">ÄÄng nháº­p</a></button>
                            </div>
                            <div class="container_button_register">
                              <button><a href="firstloginpage">Táº¡o tÃ i khoáº£n</a></button>
                            </div>
                          </div> -->
                <c:if test="${loginStatus == 'logined'}">
                    <div class="container_right">
                        <c:if test="${currentUser.role == 'S'}">
                            <div class="container_button_register">
                                <a href="createPostPage"><button>Create Post</button></a>
                            </div>
                        </c:if>
                        <c:if test="${currentUser.role == 'M'}">
                            <div class="container_button_register">
                                <a href="loadPendingPosts"><button>Pending Post</button></a>
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
                                        <a href="loadProfile?email=${currentUser.email}"
                                           ><p>Profile</p></a
                                        >
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

        <!-- Main -->

        <section class="sidebar_phone" id="sidebar_phone">
            <div class="container_sidebar_phone" id="container_sidebar_phone">
                <div class="container_toggle">
                    <h2 class="title">FPT Blog</h2>
                    <img
                        src="/images/close_button_icon.svg"
                        onclick="handleClickOutside()"
                        />
                </div>
                <div style="padding: 0.5rem">
                    <div class="introduce_community">
                        <h2 class="introduce_title">
                            <span class="brand_text">FPT Community</span> is a community of
                            690,628 amazing developers
                        </h2>
                        <p class="introduce_content">
                            We're a place where coders share, stay up-to-date and grow their
                            careers.
                        </p>
                        <div class="container_button">
                            <div class="container_button_register">
                                <button><a href="/login.html">Create account</a></button>
                            </div>
                            <div class="container_button_login">
                                <button><a href="/login.html">Log in</a></button>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar_navigation">
                        <h2 class="title_navigation">Menu</h2>
                        <div class="container_item">
                            <img src="./images/house_icon.svg" />
                            <p>Home</p>
                        </div>
                        <div class="container_item">
                            <img src="./images/hand_shake_icon.svg" />
                            <p>Log in</p>
                        </div>
                        <div class="container_item">
                            <img src="./images/list_icon.svg" />
                            <p>Pending Posts</p>
                        </div>
                    </div>
                    <div class="sidebar_navigation">
                        <h2 class="title_navigation">Common Tags</h2>
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

        <section class="main">
            <div class="left_main">
                <div class="intro">
                    <p>
                        👋 Welcome to <strong>Connect</strong>! You may message to all of
                        user here.
                    </p>
                </div>
                <div class="messageItemContainer">
                    <div class="groupItem groupItemSelected">
                        <div class="containerCover">
                            <img src="https://static.xx.fbcdn.net/rsrc.php/v3/yx/r/eKTyEU1mkTU.png"/>
                        </div>
                        <div class="nameVsLastMessage">
                            <p class="nameGroup">Mọi người đang online</p>
                        </div>
                    </div>
                    <div class="groupItem">
                        <div class="containerCover">
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIXIrqiW3R5OstWAjkuFvNwvjYHRaTmwEQWg&usqp=CAU"/>
                        </div>
                        <div class="nameVsLastMessage">
                            <p class="nameGroup">Hỗ trợ</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="right_main">
                <div class="nav">
                    <div class="groupItemNav">
                        <div class="containerCoverNav">
                            <img src="https://static.xx.fbcdn.net/rsrc.php/v3/yx/r/eKTyEU1mkTU.png"/>
                        </div>
                        <div class="nameVsLastMessage">
                            <p class="nameGroupNav">Nơi trò chuyện vui vẻ</p>
                        </div>
                    </div>
                </div>
                <div class="container_body">
                    <div class="body" id="body"></div>
                    <span id="destination"></span>
                </div>
                <div class="bottom">
                    <c:choose>
                        <c:when test="${loginStatus == 'logined'}">
                            <div class="items_container_input">
                                <textarea
                                    id="textarea"
                                    placeholder="Aa"
                                    maxlength="100"
                                    ></textarea>
                            </div>
                            <div class="items_container_like">
                                <svg
                                    viewBox="0 0 16 16"
                                    height="30"
                                    width="30"
                                    style="cursor: pointer"
                                    id="like"
                                    >
                                <path
                                    d="M16,9.1c0-0.8-0.3-1.1-0.6-1.3c0.2-0.3,0.3-0.7,0.3-1.2c0-1-0.8-1.7-2.1-1.7h-3.1c0.1-0.5,0.2-1.3,0.2-1.8 c0-1.1-0.3-2.4-1.2-3C9.3,0.1,9,0,8.7,0C8.1,0,7.7,0.2,7.6,0.4C7.5,0.5,7.5,0.6,7.5,0.7L7.6,3c0,0.2,0,0.4-0.1,0.5L5.7,6.6 c0,0-0.1,0.1-0.1,0.1l0,0l0,0L5.3,6.8C5.1,7,5,7.2,5,7.4v6.1c0,0.2,0.1,0.4,0.2,0.5c0.1,0.1,1,1,2,1h5.2c0.9,0,1.4-0.3,1.8-0.9 c0.3-0.5,0.2-1,0.1-1.4c0.5-0.2,0.9-0.5,1.1-1.2c0.1-0.4,0-0.8-0.2-1C15.6,10.3,16,9.9,16,9.1z"
                                    fill="#98A4F3"
                                    ></path>
                                <path
                                    d="M3.3,6H0.7C0.3,6,0,6.3,0,6.7v8.5C0,15.7,0.3,16,0.7,16h2.5C3.7,16,4,15.7,4,15.3V6.7C4,6.3,3.7,6,3.3,6z"
                                    fill="#98A4F3"
                                    ></path>
                                </svg>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="not_login_container">
                                <p>
                                    👋 You need to <a href="firstLoginPage">login</a> first 👋
                                </p>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </section>
        <script>
            function toggleSidebarPhone() {
                const toggle_sidebar = document.getElementById('sidebar_phone');
                toggle_sidebar.style.display = 'block';
            }
            function handleClickOutside() {
                const toggle_sidebar = document.getElementById('sidebar_phone');
                toggle_sidebar.style.display = 'none';
            }
        </script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
        <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-firestore.js"></script>
        <script type="text/javascript" src="./js/messengerPage.js"></script>
        <script>
            textarea &&
                    textarea.addEventListener('keydown', function (e) {
                        if (e.key !== 'Enter' || e.target.value.length === 0)
                            return;
                        else {
                            const {value} = e.target;
                            if (initMdl === true) {
                                addDocument(
                                        '${currentUser.name}',
                                        '${currentUser.email}',
                                        value,
                                        '${currentUser.avatar}'
                                        );
                            } else {
                                addDocumentPrivateAdmin(
                                        '${currentUser.name}',
                                        '${currentUser.email}',
                                        value,
                                        '${currentUser.avatar}'
                                        )
                            }
                        }
                        textarea.value = '';
                    });

            likeButton &&
                    likeButton.addEventListener('click', function () {
                        addDocument(
                                '${currentUser.name}',
                                '${currentUser.email}',
                                null,
                                '${currentUser.avatar}'
                                );
                    });
            // Functions
            const componentDidMount = (function () {
                let ref = false;
                return function () {
                    if (!ref) {
                        ref = true;
                        getDocumentOnMount();
                    }
                };
            })();

            $('.groupItem').click(function (e) {
                let img = $(this).find("img")[0].src;
                let content = e.target.innerText;
                $(".groupItem").toggleClass('groupItemSelected');
                $(".containerCoverNav img").attr('src', img);
                $(".nameGroupNav").text(content);
                initMdl = !initMdl;

            });

            // useEffect
            componentDidMount();

            async function getDocumentOnMount() {
                let domMessage = '';
                let messagesRealtime = [];
                await db
                        .collection('chat-global')
                        .orderBy('createdAt', 'asc')
                        .get()
                        .then((querySnapshot) => {
                            querySnapshot.forEach((doc) => {
                                messagesRealtime.push(doc.data());
                            });
                        })
                        .catch((error) => {
                            console.log('Error getting documents: ', error);
                        });
                messagesRealtime.forEach((doc, index) => {
                    if (doc.createdAt) {
                        if (index === messagesRealtime.length - 1) {
                            lastestMessageCreatedAt = doc.createdAt.seconds;
                        }
                        if ('${currentUser.email}' === doc.email) {
                            domMessage += rightMessage(doc.message);
                        } else {
                            domMessage += leftMessage(doc.avatar, doc.message, doc.name);
                        }
                    }
                });
                body.innerHTML = domMessage;
                dest.scrollIntoView({behavior: 'smooth'});
            }

               if (initMdl) {
                db.collection('chat-global')
                        .orderBy('createdAt', 'desc')
                        .limit(1)
                        .onSnapshot((querySnapshot) => {
                            let domMessage = '';
                            let messagesRealtime = [];
                            querySnapshot.forEach((doc) => {
                                if (doc.exists) {
                                    let id = doc.id;
                                    let data = {...doc.data(), id};
                                    messagesRealtime.push(data);
                                }
                            });
                            messagesRealtime.forEach((doc) => {
                                if (doc.createdAt) {
                                    if (lastestMessageCreatedAt !== doc.createdAt.seconds) {
                                        if ('${currentUser.email}' === doc.email) {
                                            domMessage += rightMessage(doc.message);
                                        } else {
                                            domMessage += leftMessage(doc.avatar, doc.message, doc.name);
                                        }
                                    }
                                }
                            });
                            if (domMessage !== '') {
                                body.insertAdjacentHTML('beforeend', domMessage);
                                dest.scrollIntoView({behavior: 'smooth'});
                            }
                        });

                    } 
               
        </script>
    </body>
</html>
