document.addEventListener("DOMContentLoaded", async () => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user || !user.student || !user.student.studentId) {
        alert("You must be enrolled as a student to view this page.");
        window.location.href = "index.html";
        return;
    }

    const studentId = user.student.studentId;
    const container = document.getElementById("enrollmentsContainer");

    try {
        const res = await fetch(`http://localhost:8080/student/${studentId}`);
        if (!res.ok) throw new Error("Failed to fetch student data");

        const student = await res.json();
        const courses = student.courses;

        if (!courses || courses.length === 0) {
            container.innerHTML = "<p>You are not enrolled in any courses yet.</p>";
            return;
        }

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
    } catch (err) {
        console.error(err);
        container.innerHTML = "<p>Error loading enrolled courses. Please try again later.</p>";
    }
});

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
