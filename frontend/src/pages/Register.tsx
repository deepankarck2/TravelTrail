import {z} from 'zod';
import {zodResolver} from '@hookform/resolvers/zod';
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import axios from "axios";

const Register = () => {

    const RegisterSchema = z.object({
        email: z.string().email({message: 'Please enter a valid email address' }),
        password: z.string().min(6, { message: 'Password must be at least 6 characters' }),
        confirmPassword: z.string().min(6, { message: 'Password must be at least 6 characters' }),
    }) .refine((data) => data.password === data.confirmPassword, {
        message: "Passwords don't match",
        path: ['confirmPassword'],
      });
    

    type FormData = z.infer<typeof RegisterSchema>;

    const { register, handleSubmit, formState: { errors } } = useForm<FormData>({
        resolver: zodResolver(RegisterSchema),
      });
      const navigate = useNavigate();

      const onSubmit = async (data: FormData) => {
        try {
          await axios.post("http://localhost:8080/api/public/register", {
            email: data.email,
            password: data.password,
          }, {
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Headers" : "Content-Type",
                },
          });
          navigate("/");
        } catch (error) {
          console.error("Registration error:", (error as any).response.data.statusCode);
          // Handle registration error (e.g., show a message to the user
        }
      };

    return (
        <div>
        <h1>Register</h1>

            <div>
            <form onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <label>Email</label>
                    <input {...register("email")} type="email"/>
                    {errors.email && <p style={{ color: "red" }}>{errors.email.message}</p>}
                </div>
                <div>
                    <label>Password</label>
                    <input type="password" {...register("password")} />
                    {errors.password && <p style={{ color: "red" }}>{errors.password.message}</p>}
                </div>
                <div>
                    <label>Retype Password</label>
                    <input type="password" {...register("confirmPassword")} />
                    {errors.confirmPassword && <p style={{ color: "red" }}>{errors.confirmPassword.message}</p>}
                </div>
                <button type="submit">Register</button>
      </form>
            </div>
        </div>
    );
}

export default Register;
