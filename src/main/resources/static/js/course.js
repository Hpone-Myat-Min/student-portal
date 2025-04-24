let courseId = null;

document.addEventListener("DOMContentLoaded", async () => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user) {
        alert("Please log in first.");
        window.location.href = "index.html";
        return;
    }

    if (user?.student?.studentId) {
        // Show student-specific links
        const studentNav = document.getElementById("studentNav");
        if (studentNav) {
            studentNav.style.display = "inline";
        }
    }

    const params = new URLSearchParams(window.location.search);
    courseId = params.get("id");

    if (!courseId) {
        alert("Invalid course ID");
        return;
    }

    const res = await fetch(`http://localhost:8080/course/${courseId}`);
    const course = await res.json();

    document.getElementById("courseTitle").textContent = course.title;
    document.getElementById("courseDescription").textContent = course.description;
    document.getElementById("courseFee").textContent = `Fee: £${course.fee}`;

    if (!user.student || !user.student.studentId) {
        // User hasn't enrolled in any course yet → can't be enrolled here
        return;
    }

    const enrolledCourses = await fetch(`/student/${user.student.studentId}/course/${courseId}/enrolled`);
    const isEnrolled = await enrolledCourses.json();

    if(isEnrolled){

        const enrollBtn = document.getElementById("enrollBtn");
        if (enrollBtn) enrollBtn.remove();

        const banner = document.createElement("div");
        banner.style.backgroundColor = "#d1ecf1";
        banner.style.color = "#0c5460";
        banner.style.padding = "1rem";
        banner.style.margin = "1rem 0";
        banner.style.border = "1px solid #bee5eb";
        banner.style.borderRadius = "5px";
        banner.textContent = "📘 You are already enrolled in this course.";
        document.body.prepend(banner);

    }

});

async function enroll() {
    const user = JSON.parse(localStorage.getItem("user"));

    const res = await fetch(`http://localhost:8080/student/enroll/${user.id}/${courseId}`, {
        method: "PUT"
    });

    if (!res.ok) {
        alert("Enrollment failed.");
        return;
    }

    const enrollBtn = document.getElementById("enrollBtn");
    if (enrollBtn) enrollBtn.remove();

    const invoice = await res.json();
    console.log("Invoice response:", invoice); // 🧪 DEBUG

    const reference = invoice.reference;
    console.log("Reference:", reference); // 🧪 DEBUG


    user.student = { studentId: invoice.studentId };
    localStorage.setItem("user", JSON.stringify(user));

    const studentNav = document.getElementById("studentNav");
    if (studentNav) studentNav.style.display = "inline";


    // let enrolledCourses = JSON.parse(localStorage.getItem("enrolledCourses")) || [];
    // if (!enrolledCourses.includes(courseId)) {
    //     enrolledCourses.push(courseId);
    //     localStorage.setItem("enrolledCourses", JSON.stringify(enrolledCourses));
    // }


    const banner = document.createElement("div");
    banner.style.backgroundColor = "#d4edda";
    banner.style.color = "#155724";
    banner.style.padding = "1rem";
    banner.style.margin = "1rem 0";
    banner.style.border = "1px solid #c3e6cb";
    banner.style.borderRadius = "5px";
    banner.textContent = `✅ You are now enrolled in this course. Invoice reference: ${reference}`;

    document.body.prepend(banner);
    // localStorage.setItem("enrolledCourseId", courseId);
    // localStorage.setItem("invoiceReference", reference);
    //
    // alert(`Enrolled successfully! Your student ID is ${student.studentId}.`);
    // window.location.href = `course.html?id=${courseId}&enrolled=true`;
}

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

