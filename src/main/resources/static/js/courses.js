document.addEventListener("DOMContentLoaded", async () => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user) {
        alert("Please log in first.");
        window.location.href = "index.html";
        return;
    }

    const urlParams = new URLSearchParams(window.location.search);
    const title = urlParams.get("title");

    let endpoint = "http://localhost:8080/course/";
    if (title) {
        endpoint = `http://localhost:8080/course/search/${encodeURIComponent(title)}`;
    }

    const res = await fetch(endpoint);
    if (!res.ok) {
        document.getElementById("coursesContainer").innerHTML = "<p>No courses found.</p>";
        return;
    }

    const raw = await res.json();
    const courses = Array.isArray(raw) ? raw : [raw];

    // const courses = Array.isArray(await res.json()) ? await res.json() : [await res.json()];

    const container = document.getElementById("coursesContainer");
    container.innerHTML = "";

    courses.forEach(course => {
        const div = document.createElement("div");
        div.className = "course";
        div.innerHTML = `
    <h3><a href="course.html?id=${course.id}">${course.title}</a></h3>
    <p>${course.description}</p>
    <p><strong>Fee:</strong> £${course.fee}</p>
  `;
        container.appendChild(div);
    });

});

// Search & Sign out (shared with other pages)
function searchCourse() {
    const query = document.getElementById("searchInput").value.trim();
    if (query) {
        window.location.href = `courses.html?title=${encodeURIComponent(query)}`;
    }
}

function signOut() {
    localStorage.removeItem("user");
    window.location.href = "index.html";
}
