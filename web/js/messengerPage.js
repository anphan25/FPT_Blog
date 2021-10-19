let textarea = eval(DomID('textarea'));
let body = eval(DomID('body'));
let dest = eval(DomID('destination'));
let likeButton = eval(DomID('like'));
let lastestMessageCreatedAt = "";
let initMdl = "chat-global";

$('textarea')
        .each(function () {
            this.setAttribute(
                    'style',
                    'height:' + this.scrollHeight + 'px;overflow-y:hidden;'
                    );
        })
        .on('input', function () {
            this.style.height = '0' + 'px';
            this.style.height = this.scrollHeight + 'px';
        });

$(`textarea`).keypress(function (e) {
    if (e.keyCode !== 13)
        return;
    var msg = $(`textarea`).val().replace(/\n/g, '');
    return false;
});

// Initialize Cloud Firestore through Firebase
firebase.initializeApp({
    apiKey: 'AIzaSyAPgZZxNDsNeVB-C6hMGKzsFelsBRIjdBI',
    authDomain: 'udemy-vue-firebase-si.firebaseapp.com',
    projectId: 'udemy-vue-firebase-si',
});

const db = eval('firebase.firestore()');

const addDocument = (name, email, message, avatar = '') => {
    db.collection('chat-global')
            .add({
                name: name,
                email: email,
                createdAt: new Date(),
                avatar: avatar,
                message: message ? message : null,
            })
            .catch((error) => {
                console.error('Error adding document: ', error);
            });
};

function DomID(element) {
    return document.getElementById(element);
}

const leftMessage = (avatar, message, name) => {
    return `<div class="left">
  <div class="avatarInChatContainer">
    <img src=${avatar} alt="avatar" />
  </div>
  <div class=${message ? 'message_container' : 'like_container'}>
  <span class="nameUser">${name}</span>
    <p>
     ${message ? message : likeIconInBody}
    </p>
  </div>
</div>`;
};

const rightMessage = (message) => {
    return `<div class="right">
  <div class=${message ? 'message_container' : 'like_container'}>
  <p>
   ${message ? message : likeIconInBody}
  </p>
</div>
</div>`;
};

const likeIconInBody = `<svg aria-labelledby="js_5" viewBox="0 0 16 16" height="30" width="30">
  <title id="js_5">Ký hiệu giơ ngón tay cái</title>
  <path
    fill="#98A4F3"
    d="M16,9.1c0-0.8-0.3-1.1-0.6-1.3c0.2-0.3,0.3-0.7,0.3-1.2c0-1-0.8-1.7-2.1-1.7h-3.1c0.1-0.5,0.2-1.3,0.2-1.8 c0-1.1-0.3-2.4-1.2-3C9.3,0.1,9,0,8.7,0C8.1,0,7.7,0.2,7.6,0.4C7.5,0.5,7.5,0.6,7.5,0.7L7.6,3c0,0.2,0,0.4-0.1,0.5L5.7,6.6 c0,0-0.1,0.1-0.1,0.1l0,0l0,0L5.3,6.8C5.1,7,5,7.2,5,7.4v6.1c0,0.2,0.1,0.4,0.2,0.5c0.1,0.1,1,1,2,1h5.2c0.9,0,1.4-0.3,1.8-0.9 c0.3-0.5,0.2-1,0.1-1.4c0.5-0.2,0.9-0.5,1.1-1.2c0.1-0.4,0-0.8-0.2-1C15.6,10.3,16,9.9,16,9.1z"
  ></path>
  <path
    fill="#98A4F3"
    d="M3.3,6H0.7C0.3,6,0,6.3,0,6.7v8.5C0,15.7,0.3,16,0.7,16h2.5C3.7,16,4,15.7,4,15.3V6.7C4,6.3,3.7,6,3.3,6z"
  ></path>
</svg>`;
