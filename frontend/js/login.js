document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    const res = await fetch(`http://localhost:8080/user/loginUser?username=${username}&password=${password}`, {
        method: "POST",
    });

    if (!res.ok) {
        alert("Login failed. Please check your username and password.");
        return;
    }

    const user = await res.json();
    localStorage.setItem("user", JSON.stringify(user));

    alert("Login successful!");
    window.location.href = "welcome.html";
});
