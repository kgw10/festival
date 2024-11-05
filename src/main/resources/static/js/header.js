// 로그인 상태를 저장할 변수
let isLoggedIn = false;

// 로그인 버튼 클릭 시 호출되는 함수
function handleLogin() {
    const userId = document.getElementById("userId").value;
    const password = document.getElementById("password").value;

    fetch('/member/signIn', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `userId=${encodeURIComponent(userId)}&password=${encodeURIComponent(password)}`
    })
    .then(response => {
        if (response.ok) {
            isLoggedIn = true;
            updateLoginLogoutButton();
            window.location.reload(); // 페이지 리로드하여 로그인 상태 반영
        } else {
            alert('로그인 실패');
        }
    })
    .catch(error => console.error("Error:", error));
}

// 로그아웃 버튼 클릭 시 호출되는 함수
function handleLogout() {
    fetch('/member/logout', {
        method: 'POST'
    })
    .then(response => {
        if (response.ok) {
            isLoggedIn = false;
            updateLoginLogoutButton();
            window.location.reload(); // 페이지 리로드하여 로그인 상태 반영
        } else {
            alert('로그아웃 실패');
        }
    })
    .catch(error => console.error("Error:", error));
}

// 로그인/로그아웃 버튼의 텍스트와 동작을 업데이트하는 함수
function updateLoginLogoutButton() {
    const loginLogoutButton = document.getElementById("loginLogoutButton");
    if (isLoggedIn) {
        loginLogoutButton.textContent = "로그아웃";
        loginLogoutButton.setAttribute("onclick", "handleLogout()");
    } else {
        loginLogoutButton.textContent = "로그인";
        loginLogoutButton.removeAttribute("onclick"); // onclick 속성 제거
    }
}

// 페이지 로드 시 버튼 상태 초기화
window.onload = updateLoginLogoutButton;