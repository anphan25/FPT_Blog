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

        <link rel="stylesheet" href="./styles/index.css" />
        <title>Home | FPT Blog</title>
    </head>
    <body>
        <c:set var="loginStatus" value="${sessionScope.LOGIN}"/>
        <c:set var="currentUser" value="${sessionScope.CURRENT_USER}"/>
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
                            <c:forEach var="cateDTO" items="${sessionScope.CATEGORY_LIST}" >
                                <div class="dropdown_category_item">
                                    <a href="searchByCategory?categoryId=${cateDTO.ID}">${cateDTO.name}</a>
                                </div>  
                            </c:forEach>
                        </div>
                    </div>
                    <div class="container_searchBar">
                        <form id="searchit" action="searchTitle">        
                            <input placeholder="Search..." name="titleValue" autocomplete="off"/>
                            <div class="container_icon" onclick="submit_form()">
                                <i class="fas fa-search"></i>
                            </div>
                        </form>
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
                                <a href="loadPendingPosts?postStatus=WFA"><button>Pending Post</button></a>
                            </div>
                        </c:if>
                        <c:if test="${currentUser.role == 'A'}">
                            <div class="container_button_register">
                                <a href="createCategoryPage"
                                   ><button>Create Category</button></a
                                >
                            </div>
                        </c:if>
                        <a href="messengerPage">
                            <div class="icon_notification_container">
                                <img src="./images/chat.svg" />
                            </div>
                        </a>
                        <div class="dropdown">
                            <div class="dropbtn_noti">
                                <img src="./images/notification_icon.svg" />
                                <div id="warning" class="warning warning-hidden">!</div>
                            </div>
                            <div class="dropdown-content1">
                            </div>
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
                            <span class="brand_text">FPT Community</span> is a place where students can share knowledge and learn from each other
                        </h2>
                        <p class="introduce_content">
                            We're a place where students share, stay up-to-date and grow their
                            careers.
                        </p>
                        <div class="container_button">
                            <a href="firstloginpage">
                                <div class="container_button_register">
                                    <button>Create account</button>
                                </div>
                            </a>
                            <a href="firstloginpage">
                                <div class="container_button_login">
                                    <button>Log in</button>
                                </div>
                            </a>
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
            <div class="container_main">
                <div class="main_container_left">
                    <c:if test="${loginStatus != 'logined'}">
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
                                    <button><a href="registerPage">Create account</a></button>
                                </div>
                                <div class="container_button_login">
                                    <button><a href="firstLoginPage">Log in</a></button>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="sidebar_navigation">
                        <h2 class="title_navigation">Menu</h2>
                        <a href="loadBlogs">
                            <div class="container_item">
                                <img src="./images/house_icon.svg" />
                                <p>Home</p>
                            </div>
                        </a>
                        <c:if test="${loginStatus == 'logined'}">
                            <c:if test="${currentUser.role == 'S'}">
                                <a href="createPostPage">
                                    <div class="container_item create-post">
                                        <img src="./images/create-blog.svg" />
                                        <p>Create Post</p>
                                    </div>
                                </a>
                                <a href="loadRejectedPosts">
                                    <div class="container_item create-post">
                                        <img src="./images/post-management.png" />
                                        <p>Rejected Posts</p>
                                    </div>
                                </a>
                            </c:if>
                            <c:if test="${currentUser.role == 'M'}">
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
                                <a href="loadDashboard?tab=post">
                                    <div class="container_item create-post">
                                        <img src="./images/dashborad.svg" />
                                        <p>Give Award</p>
                                    </div>
                                </a>
                            </c:if>
                            <c:if test="${currentUser.role == 'A'}">
                                <a href="createCategoryPage">
                                    <div class="container_item">
                                        <img src="./images/category_icon.svg" />
                                        <p>Create Category</p>
                                    </div>
                                </a>
                                <a href="loadUserList">
                                    <div class="container_item user-list-icon">
                                        <img src="./images/user-list.svg" />
                                        <p>User List</p>
                                    </div>                                   
                                </a>
                                <a href="loadAllComments">
                                    <div class="container_item user-list-icon">
                                        <img src="./images/comment.png" />
                                        <p>Comments Management</p>
                                    </div>
                                </a>
                            </c:if>
                        </c:if>
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
                <div class="main_container_middle">
                    <div class="filter_navigation_middle">
                        <h2>Posts</h2>
                        <div class="filter_by_range">
                            <div class="item item-selected">
                                <p class="selected">Feed</p>
                            </div>

                        </div>
                    </div>
                    <div class="container_posts">
                        <c:set var="blogs" value="${requestScope.LIST_PAGING}"/>
                        <c:forEach var="blogDTO" items="${blogs}">
                            <div class="post">

                                <div class="container_info_post">
                                    <div class="user_info">
                                        <div class="container_avatar">
                                            <img
                                                src="${blogDTO.avatar}"
                                                />
                                        </div>
                                        <div class="container_name_date_post">
                                            <div class="name-award">
                                                <a href="loadProfile?email=${blogDTO.emailPost}">                                              
                                                    <p class="username">${blogDTO.namePost}</p>
                                                </a>
                                                <p class="user-award">
                                                    <c:if test="${not empty blogDTO.awards}">
                                                        <c:forEach var="award" items="${blogDTO.awards}">
                                                            <c:if test="${award == 1}">
                                                                <img src="./images/contribution-award.svg" alt=""/>
                                                            </c:if>
                                                            <c:if test="${award == 2}">
                                                                <img src="./images/heart-award.png" alt=""/>
                                                            </c:if>
                                                            <c:if test="${award == 3}">
                                                                <img src="./images/top-author.png" alt=""/>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </p>
                                            </div>
                                            <p class="date_posted">${blogDTO.approvedDate}</p>
                                        </div>
                                    </div>
                                    <div class="post_info">
                                        <a href="loadPostContent?postId=${blogDTO.ID}">
                                            <h1 class="title_post">
                                                ${blogDTO.title}
                                            </h1>
                                        </a>
                                        <div class="hashtag">
                                            <c:forEach var="tag" items="${blogDTO.tag}">

                                                <a href="searchByTag?tag=${tag}">
                                                    <p><span class="hash"></span>#${tag}</p>
                                                </a>
                                            </c:forEach>
                                        </div>
                                        <div class="statistic">
                                            <div class="reaction_and_comments">
                                                <div>
                                                    <img src="./images/vote_icon.svg" />
                                                    <p>
                                                        ${blogDTO.likes}
                                                        <span class="text_comments_votes">Likes</span>
                                                    </p>
                                                </div>
                                                <div>
                                                    <img src="./images/comment_icon.svg" />
                                                    <p>
                                                        ${blogDTO.comments}
                                                        <span class="text_comments_votes">Comments</span>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="time_and_save">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                     

                        </c:forEach>
                    </div>

                    <div class="pagingIndex">
                        <c:set var="activeIndex" value="${requestScope.CHECK_INDEX}"/>
                        <c:forEach begin="1" end="${requestScope.ENDPAGE}" var="i">
                            <a class="${activeIndex == i ? "active": ""} indexPage" href="loadBlogs?index=${i}">${i}</a>
                        </c:forEach>
                    </div>
                </div>


                <div class="main_container_right">
                    <div class="news">
                        <h2 class="title">Common posts</h2>
                        <c:forEach var="commonPost" items="${requestScope.COMMON_POSTS}">
                            <a href="loadPostContent?postId=${commonPost.ID}">
                                <div class="item">
                                    <h2 class="title_post">
                                        ${commonPost.title}
                                    </h2>
                                    <div class="like-cmt">
                                        <p>${commonPost.likes} Likes</p>
                                        <p>${commonPost.comments} Comments</p>
                                    </div>
                                    <div class="container_news_button">
                                        <p class="news_button">Hot</p>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
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

        <script>
            function toggleSidebarPhone() {
                const toggle_sidebar = document.getElementById('sidebar_phone');
                toggle_sidebar.style.display = 'block';
            }
            function handleClickOutside() {
                const toggle_sidebar = document.getElementById('sidebar_phone');
                toggle_sidebar.style.display = 'none';
            }
            function submit_form()
            {
                var form = document.getElementById("searchit");
                form.submit();
            }
            //ko cho resubmiss đầu bùi browser rác
            if (window.history.replaceState)
            {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js"></script>
                <script src="https://www.gstatic.com/firebasejs/8.10.0/firebase-firestore.js"></script>
        <script>
            // Initialize Firebase
            firebase.initializeApp({
                apiKey: 'AIzaSyAPgZZxNDsNeVB-C6hMGKzsFelsBRIjdBI',
                authDomain: 'udemy-vue-firebase-si.firebaseapp.com',
                projectId: 'udemy-vue-firebase-si',
            });
            const db = eval('firebase.firestore()');
            const notiWrapper = document.querySelector(".dropdown-content1");
            let lastestNotiCreatedAt = "";
            let componentRunOnDepend = false;
            let lol= false;
            let currentUser = `${currentUser.email}`;
            currentUser = currentUser.substr(0, currentUser.indexOf("@"));
            const itemNoti = (avatar, user, action, postID, createdAt) => {
                return (
                        ` <a href="loadPostContent?postId=\${postID}">
                            <div class="noti_item">
                                <img class="noti_other_user"  src="\${avatar}"/>
                                  <div>
                                     <p><b>\${user}</b> đã <b>\${action}</b> bài viết của bạn</p>
                                    <p style="font-size: 14px; margin-top: 0.2rem">\${createdAt}</p>
                                  </div>
                            </div>
                        </a>`
                        )
            }
            
             const itemNotiNew = (avatar, user, action, postID, createdAt) => {
                return (
                        ` <a href="loadPostContent?postId=\${postID}">
                            <div class="noti_item_new">
                                <img class="noti_other_user"  src="\${avatar}"/>
                                  <div>
                                     <p><b>\${user}</b> đã <b>\${action}</b> bài viết của bạn</p>
                                    <p style="font-size: 14px; margin-top: 0.2rem">\${createdAt}</p>
                                  </div>
                            </div>
                        </a>`
                        )
            }

            $(".dropbtn_noti").hover(function (e) {
                $("#warning").addClass("warning-hidden");
            });
            // Functions
            const componentDidMount = (function () {
                let ref = false;
                return function () {
                    if (!ref) {
                        ref = true;
                        componentRunOnDepend = true;
                        getDocumentOnMount();
                    }
                };
            })();

            // useEffect
            componentDidMount();

            async function getDocumentOnMount() {
                let domMessage = '';
                let notifyRealtime = [];
                await db
                        .collection('notify')
                        .doc(currentUser)
                        .collection("incoming")
                        .orderBy('createdAt', 'desc')
                        .limit(5)
                        .get()
                        .then((querySnapshot) => {
                            querySnapshot.forEach((doc) => {
                                notifyRealtime.push(doc.data());
                            });
                        })
                        .catch((error) => {
                            console.log('Error getting documents: ', error);
                        });
                if (notifyRealtime.length > 0) {
                    notifyRealtime.forEach((doc, index) => {
                        if (doc.createdAt) {
                            if (index === notifyRealtime.length - 1) {
                                lastestNotiCreatedAt = doc.createdAt.seconds;
                            }
                            var date = new Date(doc.createdAt.toDate()).toLocaleString("en-GB", {year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit'});
                            domMessage += itemNoti(doc.avatar, doc.user, doc.action, doc.postId, date);

                        }
                    });
                } else {
                    domMessage += `<div class="noti_item">
                                        <p></p>
                                    </div>
                                </div>`;
                }
                notiWrapper.innerHTML = domMessage;
            }

            if (componentRunOnDepend) {
                db.collection('notify')
                        .doc(currentUser)
                        .collection("incoming")
                        .orderBy('createdAt', 'desc')
                        .limit(1)
                        .onSnapshot((querySnapshot) => {
                            let domMessage = '';
                            let notifyRealtime = [];
                            querySnapshot.forEach((doc) => {
                                if (doc.exists) {
                                    let id = doc.id;
                                    let data = {...doc.data(), id};
                                    notifyRealtime.push(data);
                                }
                            });
                            notifyRealtime.forEach((doc, index) => {
                                if (doc.createdAt) {
                                    console.log(lastestNotiCreatedAt, doc.createdAt.seconds);
                                    if (lol) {
                                        var date = new Date(doc.createdAt.toDate()).toLocaleString("en-GB", {year: 'numeric', month: 'numeric', day: 'numeric', hour: '2-digit', minute: '2-digit'});
                                        domMessage += itemNotiNew(doc.avatar, doc.user, doc.action, doc.postId, date);
                                    }
                                    lol = true;
                                }
                            });
                            if (domMessage !== '') {
                                notiWrapper.insertAdjacentHTML('afterbegin', domMessage);
                                $("#warning").removeClass("warning-hidden");
                            }
                        });
                    }
        </script>
    </body>
</html>
