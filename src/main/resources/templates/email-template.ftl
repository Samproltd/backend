<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IT Asset Management Certificate</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            width: 800px;
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 40px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative;
            text-align: center;
        }
        .header {
            margin-bottom: 30px;
        }
        .title {
            font-size: 36px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }
        .subtitle {
            font-size: 24px;
            color: #555;
            margin-bottom: 30px;
        }
        .content {
            font-size: 18px;
            line-height: 1.6;
            color: #333;
        }
        .name {
            font-size: 30px;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 20px;
        }
        .presented-by {
            margin-top: 40px;
        }
        .presented-by img {
            max-width: 200px;
            margin-bottom: 10px;
        }
        .presented-by p {
            font-size: 16px;
            color: #555;
        }
        .signature {
            margin-top: 30px;
            text-align: right;
        }
        .signature img {
            max-width: 200px;
        }
        .footer {
            margin-top: 40px;
            text-align: center;
            font-size: 14px;
            color: #777;
        }
        .ribbon {
            position: absolute;
            top: 20px;
            right: 20px;
            background-color: #ffd700;
            padding: 10px 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            font-weight: bold;
            color: #333;
        }
        .border-left, .border-right, .border-top, .border-bottom {
            position: absolute;
            background: linear-gradient(to bottom, #007bff, #66a3ff);
        }
        .border-left {
            top: 0;
            left: 0;
            width: 20px;
            height: 100%;
        }
        .border-right {
            top: 0;
            right: 0;
            width: 20px;
            height: 100%;
        }
        .border-top {
            top: 0;
            left: 0;
            width: 100%;
            height: 20px;
        }
        .border-bottom {
            bottom: 0;
            left: 0;
            width: 100%;
            height: 20px;
        }
        .logo-left {
            position: absolute;
            top: 20px;
            left: 20px;
            max-width: 100px;
        }
        .logo-right {
            position: absolute;
            top: 20px;
            right: 20px;
            max-width: 100px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="border-left"></div>
        <div class="border-right"></div>
        <div class="border-top"></div>
        <div class="border-bottom"></div>

        <div class="logo-left">
            <img src="" alt="Logo Left">
        </div>
        

        <div class="ribbon">
            <span>ITAM</span>
            <span>SAM</span>
            <span>HAM</span>
        </div>

        <div class="header">
            <h1 class="title">CERTIFICATE</h1>
            <p class="subtitle">OF COMPLETION</p>
        </div>

        <div class="content">
            <p>This is to certify that</p>
            <p class="name" id="certificateName"></p>
            <p>has successfully completed the <span id="certificateSubject"></span> training from <b>SAMPRO LICENSE INDIA PVT LTD.</b> in recognition of their dedication to enhancing their skills & knowledge in IT Asset Management. This certificate is awarded to acknowledge their achievement & commitment to excellence.</p>
            <p>Certificate Number: <span id="certificateNumber"></span></p>
            <p>Issue Date: <span id="issueDate"></span></p>
        </div>

        <div class="presented-by">
            <img src="" alt="SAMPRO Logo">
            <p>SAMPRO License India Private Limited</p>
        </div>

        <div class="signature">
            <img src="" alt="Mayur Chaudhari Signature">
        </div>

        <div class="footer">
            <p>www.samprolicense.com | www.training.samprolicense.com | +91 9028224136</p>
        </div>
    </div>

    <script>
        // Function to generate a random certificate number
        function generateCertificateNumber() {
            const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
            let result = '';
            for (let i = 0; i < 10; i++) {
                result += characters.charAt(Math.floor(Math.random() * characters.length));
            }
            return result;
        }

        // Function to get the current date in a readable format
        function getLocalDate() {
            const now = new Date();
            const options = { year: 'numeric', month: 'long', day: 'numeric' };
            return now.toLocaleDateString(undefined, options);
        }

        // Function to populate the certificate details
        function populateCertificate(name, subject) {
            document.getElementById('certificateName').textContent = name;
            document.getElementById('certificateSubject').textContent = subject;
            document.getElementById('certificateNumber').textContent = generateCertificateNumber();
            document.getElementById('issueDate').textContent = getLocalDate();
        }

        // Sample data (Replace with actual data from user input)
        document.addEventListener('DOMContentLoaded', function() {
            const sampleName = "John Doe"; // Replace with dynamic user input
            const sampleSubject = "IT Asset Management"; // Replace with dynamic subject

            populateCertificate(sampleName, sampleSubject);
        });
    </script>
</body>
</html>
