document.addEventListener("DOMContentLoaded", () => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user) {
        alert("Please log in first.");
        window.location.href = "index.html";
        return;
    }

    // Display welcome message
    const welcome = document.getElementById("welcomeMsg");
    welcome.textContent = `Hello, ${user.username}! Welcome to the Student Portal.`;
});

// Course search function
function searchCourse() {
    const query = document.getElementById("searchInput").value.trim();
    if (query) {
        window.location.href = `course.html?title=${encodeURIComponent(query)}`;
    }
}

// Sign out
function signOut() {
    localStorage.removeItem("user");
    window.location.href = "index.html";
}
