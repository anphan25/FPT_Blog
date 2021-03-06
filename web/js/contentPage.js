const textareaComment = document.querySelector("#textarea-cmt");

const userComment = document.querySelector(".current-user-comment");
const submitBtn = document.querySelector(".submit-btn");
const likeIcon = document.querySelector(".like-icon");
const cmtIcon = document.querySelector(".cmt-icon");
const loginModal = document.querySelector(".login-modal");
const overlay = document.querySelector(".overlay");
const closeBtnModal = document.querySelector(".close-btn-modal-div");
const header = document.querySelector("header");
const userCommentItem = document.querySelector(".user-comment-item");
const deleteDiv = document.querySelector(".delete-div");
const deleteModal = document.querySelector(".delete-modal");
const deleteOverlay = document.querySelector(".delete-overlay");
const delForm = document.querySelector("#del-form");
const deltextArea = document.querySelector(".del-reason-text");
const delReason = document.querySelector(".reason-del-input");
const delBtn = document.querySelector(".del-btn");

submitBtn.addEventListener("mouseenter", () => {
    submitBtn.style.backgroundColor = "#323ebe";
});

submitBtn.addEventListener("mouseleave", () => {
    submitBtn.style.backgroundColor = "#3B49DF";
});

cmtIcon.addEventListener("click", () => {
    userComment.scrollIntoView({behavior: "smooth", block: "center"});
    textareaComment.focus();
});

//Functions
//Functions
//Functions
const toggleModalHidden = () => {
    overlay.classList.toggle("hidden");
    loginModal.classList.toggle("hidden");
};

function checkWordCount() {
    if (textareaComment.value == "") {
        disableSubmitBtn();
    } else {
        submitBtn.disabled = false;
        submitBtn.style.backgroundColor = "#3B49DF";
    }
}

const disableDelBtn = () => {
    delBtn.style.backgroundColor = "#f78282";
    delBtn.disabled = true;
};

const checkWordCountDel = () => {
    if (deltextArea.value == "") {
        disableDelBtn();
    } else {
        delBtn.disabled = false;
        delBtn.style.backgroundColor = "#dc1818";
    }
};

const checkLoginKind = () => {
    if (header.getAttribute("login-kind") === "no-login") {
        likeIcon.addEventListener("click", () => {
            toggleModalHidden();
        });

        userCommentItem.addEventListener("click", () => {
            toggleModalHidden();
            textareaComment.disabled = true;
        });

        overlay.addEventListener("click", () => {
            toggleModalHidden();
        });

        closeBtnModal.addEventListener("click", () => {
            toggleModalHidden();
        });

        cmtIcon.addEventListener("click", () => {
            toggleModalHidden();
        });
    }
    if (header.getAttribute("login-kind") === "logined") {
        textareaComment.addEventListener("keyup", () => {
            checkWordCount();
        });

        submitBtn.addEventListener("click", (e) => {
            // e.preventDefault();
            disableSubmitBtn();
            textareaComment.value = "";
        });

        textareaComment.addEventListener("focus", () => {
            textareaComment.style.backgroundColor = "#FFFFFF";
            textareaComment.style.outlineColor = "#3B49DF";
            submitBtn.classList.remove("hidden");
            textareaComment.style.height = "135px";
        });

        textareaComment.addEventListener("blur", () => {
            textareaComment.style.backgroundColor = "#EFEFEF";
            textareaComment.style.border = "0.5px solid #BDBDBD";
        });

        likeIcon.addEventListener("click", (e) => {
            likeIcon.classList.toggle("clicked-like-icon");
            likeIcon.nextElementSibling.classList.toggle("clicked-like-text");
        });

        deltextArea.addEventListener("keyup", () => {
            checkWordCountDel();
        });
    }
};

const disableSubmitBtn = () => {
    submitBtn.style.backgroundColor = "#8992EC";
    submitBtn.disabled = true;
};

checkLoginKind();
disableSubmitBtn();
disableDelBtn();

if (deleteDiv) {
    deleteDiv.addEventListener("click", () => {
        deleteModal.classList.toggle("hidden");
        deleteOverlay.classList.toggle("hidden");
        deltextArea.focus();
    });

    deleteOverlay.addEventListener("click", () => {
        deleteModal.classList.toggle("hidden");
        deleteOverlay.classList.toggle("hidden");
    });
}
document.querySelector(".cancel-btn").addEventListener("click", () => {
    deleteModal.classList.toggle("hidden");
    deleteOverlay.classList.toggle("hidden");
});

delForm.addEventListener("submit", (e) => {
    e.preventDefault();
    delReason.value = deltextArea.value;
    delForm.submit();
});





