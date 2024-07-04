import React, { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const loginSchema = z.object({
  email: z.string().email({ message: 'Please enter a valid email address' }),
  password: z.string().min(6, { message: 'Password must be at least 6 characters' }),
});

type FormData = z.infer<typeof loginSchema>;

const Login: React.FC = () => {
  const [message, setMessage] = useState<string>('');
  const { register, handleSubmit, formState: { errors } } = useForm<FormData>({
    resolver: zodResolver(loginSchema),
  });
  const navigate = useNavigate();

  const onSubmit = async (data: FormData) => {
    try {
      const response = await axios.post("http://localhost:8080/api/public/login", {
        email: data.email,
        password: data.password,
      }, {
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Headers": "Content-Type",
        },
      });

      // Store the token in local storage
      localStorage.setItem("authToken", response.data.token);
      setMessage("Login successful!");
      navigate("/home");
    } catch (error) {
      if (axios.isAxiosError(error) && error.response?.status === 401) {
        setMessage("Incorrect username or password.");
      } else {
        setMessage("An error occurred. Please try again later.");
      }
    }
  };

  return (
    <div>
      <h1>Login</h1>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div>
          <label>Email</label>
          <input {...register("email")} type="email" />
          {errors.email && <p style={{ color: "red" }}>{errors.email.message}</p>}
        </div>
        <div>
          <label>Password</label>
          <input type="password" {...register("password")} />
          {errors.password && <p style={{ color: "red" }}>{errors.password.message}</p>}
        </div>
        <button type="submit">Login</button>
      </form>
      {message && <p style={{ color: "red" }}>{message}</p>}
    </div>
  );
};

export default Login;
