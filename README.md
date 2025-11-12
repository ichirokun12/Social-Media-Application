<h1 align="center">🌐 Social Media Application</h1>

<p align="center">
  A clean and minimal social media backend built with <b>Spring Boot</b> and <b>Java</b>.<br>
  Designed to be extendable, secure, and easy to learn from.
</p>

---

<h2>🚀 Overview</h2>

<p>
A simple social media backend where users can:
</p>

<ul>
  <li>Register and log in securely</li>
  <li>Create, edit, and delete posts</li>
  <li>Like and comment on other posts</li>
  <li>Manage profiles</li>
  <li>View a personalized feed</li>
</ul>

---

<h2>🛠️ Tech Stack</h2>

<table>
  <tr><td><b>Backend</b></td><td>Spring Boot, Spring Security, JPA (Hibernate)</td></tr>
  <tr><td><b>Database</b></td><td>MySQL / H2</td></tr>
  <tr><td><b>Build Tool</b></td><td>Maven</td></tr>
  <tr><td><b>Language</b></td><td>Java 17+</td></tr>
</table>

---

<h2>⚙️ Setup & Run</h2>

<pre>
git clone https://github.com/RajatSingh1205/Social-Media-Application.git
cd Social-Media-Application
mvn clean install
mvn spring-boot:run
</pre>

<p>
Then visit: <code>http://localhost:8080</code>
</p>

---

<h2>🔐 Security</h2>

<ul>
  <li>Spring Security for authentication and authorization</li>
  <li>Password hashing using BCrypt</li>
  <li>Endpoints secured for logged-in users</li>
  <li>Extendable to JWT or OAuth2 authentication</li>
</ul>

---

<h2>📁 Folder Structure</h2>

<pre>
src/
 ├── main/
 │   ├── java/com/example/socialmedia/
 │   │    ├── controller/
 │   │    ├── service/
 │   │    ├── repository/
 │   │    └── entity/
 │   └── resources/
 │        ├── application.properties
 │        └── static/
</pre>

---

<h2>🚧 Future Enhancements</h2>

<ul>
  <li>Image uploads (AWS S3 or local storage)</li>
  <li>Real-time chat with WebSocket</li>
  <li>Pagination and infinite scrolling</li>
  <li>Follow/Unfollow system</li>
  <li>Admin dashboard</li>
</ul>

---

<h2>🤝 Contributing</h2>

<p>
Pull requests are welcome! Please follow Java best practices and meaningful commit messages.<br>
If you find a bug or have an idea, open an issue on GitHub.
</p>

---

<h2>📬 Contact</h2>

<p>
<b>GitHub:</b> <a href="https://github.com/RajatSingh1205">RajatSingh1205</a><br>
<b>Email:</b> rajat.k.singh1209@gmail.com
</p>

---

<p align="center">
  <i>Minimal • Clean • Extendable</i><br>
  Made with ❤️ by <b>Rajat Kumar Singh</b>
</p>
