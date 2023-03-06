import React, {useState} from "react";

export const Register = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [name, setName] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);
    }

    return (
        <div className="auth-form-container">
            <h2>Login</h2>
            <form className="register-form" onSubmit={handleSubmit}>
                <label htmlFor="name">Username</label>
                <input value={name} name="name" id="name" placeholder="Username" />

                <label htmlFor="email">Email</label>
                <input value={email} type="email" placeholder="yourEmail@gmail.com" id="email" name="email"/>

                <label htmlFor="password">Password</label>
                <input value={pass} type="password" placeholder="Password" id="password" name="password" />

                <button>Register</button>
            </form>
            <button className="link-button" onClick={() => props.onFormSwitch('login')}>Already have an account? Login here.</button>
        </div>
    )
}