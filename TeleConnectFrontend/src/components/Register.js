import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register() {
  const [firstName, setFirstName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [aadharNumber, setAadharNumber] = useState('');
  const [aadharImage, setAadharImage] = useState(null);
  const fileInputRef = useRef(null); // Ref for file input
  const navigate = useNavigate();

  useEffect(() => {
    const verifiedEmail = sessionStorage.getItem('verifiedEmail');
    if (verifiedEmail) {
      setEmail(verifiedEmail);
    } else {
      alert('Email not verified. Please verify your email before registration.');
      navigate('/email-verification');
    }
  }, [navigate]);

  const handleAadharImageChange = (event) => {
    const file = event.target.files[0];
    const fileType = file.type;

    if (fileType === 'image/jpeg' || fileType === 'image/png') {
      setAadharImage(file);
    } else {
      alert('Only JPG and PNG files are allowed.');
      fileInputRef.current.value = ''; // Clear file input
    }
  };

  const handleRegister = async (event) => {
    event.preventDefault();

    const aadharPattern = /^\d{12}$/; 
    if (!aadharPattern.test(aadharNumber)) {
      alert('Invalid Aadhaar number. It should be 12 digits.');
      return;
    }

    if (password.length < 6) {
      alert('Password should be at least 6 characters.');
      return;
    }

    if (!aadharImage) {
      alert('Please upload your Aadhaar file.');
      return;
    }

    try {
      const formData = new FormData();
      formData.append('firstName', firstName.trim());
      formData.append('email', email.trim());
      formData.append('password', password.trim());
      formData.append('mobileNumber', mobileNumber.trim());
      formData.append('aadharNumber', aadharNumber.trim());
      formData.append('aadharImage', aadharImage);

      const response = await axios.post('http://3.92.50.211:8083/api/user/register', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      console.log(response.data);
      alert('Registration successful');
      navigate('/login');
    } catch (error) {
      console.error('Error submitting form:', error);
      alert('Registration failed');
    }
  };

  return (
    <div className="whole">
      <div className="form-container">
        <h1>Registration</h1>
        <form onSubmit={handleRegister}>
          <div className="form-group">
            <label>First Name</label>
            <input
              type="text"
              id="firstName"
              name="firstName"
              placeholder="Enter First Name"
              value={firstName}
              onChange={(event) => setFirstName(event.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              id="email"
              name="email"
              placeholder="Enter Email"
              value={email}
              required
              readOnly
            />
          </div>
          <div className="form-group">
            <label>Aadhaar Number</label>
            <input
              type="text"
              id="aadharNumber"
              name="aadharNumber"
              placeholder="Enter Aadhaar number"
              value={aadharNumber}
              onChange={(event) => setAadharNumber(event.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Mobile Number</label>
            <input
              type="text"
              id="mobileNumber"
              name="mobileNumber"
              placeholder="Enter Mobile number"
              value={mobileNumber}
              onChange={(event) => setMobileNumber(event.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Enter password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label>Upload Aadhaar</label>
            <input
              type="file"
              id="aadharImage"
              name="aadharImage"
              ref={fileInputRef} // Use ref to clear the input
              onChange={handleAadharImageChange}
              required
            />
          </div>
          <button type="submit" className="btn btn-primary">Register</button>
        </form>
      </div>
    </div>
  );
}

export default Register;
