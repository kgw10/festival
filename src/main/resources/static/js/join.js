// 이용약관 동의 시 회원가입 입력 폼 표시
function showSignUpForm() {
    const agreeCheckbox = document.getElementById("agreeTerms");
    const signUpForm = document.querySelector(".signUpForm");
    const formData = document.querySelector(".formData");

    // 약관에 동의하면 회원가입 입력 폼을 표시하고 약관 부분을 숨깁니다.
    if (agreeCheckbox && agreeCheckbox.checked) {
        if (signUpForm) signUpForm.style.display = "block";
        if (formData) formData.style.display = "none";
    } else {
        alert("이용약관에 동의해야 다음으로 진행할 수 있습니다.");
    }
}

// 아이디 중복 확인
function checkDuplicateId() {
    const userIdField = document.querySelector("input[th\\:field='*{id}']");
    if (userIdField) {
        const userId = userIdField.value.trim(); // trim()으로 공백 제거
        if (!userId) {
            alert("아이디를 입력해주세요.");
            return;
        }
        fetch(`/member/checkDuplicateId?userId=${userId}`)
            .then(response => response.json())
            .then(isDuplicate => {
                if (isDuplicate) {
                    alert("이미 사용 중인 아이디입니다.");
                } else {
                    alert("사용 가능한 아이디입니다.");
                }
            })
            .catch(error => console.error("Error:", error));
    }
}

// 닉네임 중복 확인
function checkDuplicateNickname() {
    const nicknameField = document.querySelector("input[th\\:field='*{nickname}']");
    if (nicknameField) {
        const nickname = nicknameField.value.trim(); // trim()으로 공백 제거
        if (!nickname) {
            alert("닉네임을 입력해주세요.");
            return;
        }
        fetch(`/member/checkDuplicateNickname?nickname=${nickname}`)
            .then(response => response.json())
            .then(isDuplicate => {
                if (isDuplicate) {
                    alert("이미 사용 중인 닉네임입니다.");
                } else {
                    alert("사용 가능한 닉네임입니다.");
                }
            })
            .catch(error => console.error("Error:", error));
    }
}