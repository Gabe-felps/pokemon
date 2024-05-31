'use client'

import { useState } from "react"
import userService from "./services/userService";
import { useCookies } from "next-client-cookies";
import { useRouter } from 'next/navigation'

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const cookies = useCookies();
  const router = useRouter()

  const submitValues = async () => {
    try {
      const response = await userService.post("/login", {
        email: email,
        password: password
      })
      if (response.status === 200) {
        cookies.set("token", response.data.token);
        router.push("/pokemon")
      }
    }
    catch (error) {
      window.alert("Falha no Login")
    }
  }

  return (
    <main className="flex min-h-screen items-center justify-center">
      <div className="flex flex-col w-full h-full items-center justify-center p-12">
        <div className="p-20 w-1/2 bg-slate-300">
          <div className="flex justify-center w-full p-2">
            <label htmlFor="email">Email: </label>
            <input
              id="email"
              type="text"
              onChange={event => setEmail(event.target.value)}
              value={email}
            />
          </div>
          <div className="flex justify-center w-full p-2">
            <label htmlFor="password">Senha: </label>
            <input
              id="password"
              type="password"
              onChange={event => setPassword(event.target.value)}
              value={password}
            />
          </div>
          <div className="flex justify-center w-full p-2">
            <button className="border-2 border-black w-1/2 bg-white" onClick={submitValues}>Entrar</button>
          </div>
          <div className="flex justify-center w-full p-2">
            <button onClick={() => {router.push("/register")}}>Não possuo conta</button>
          </div>
        </div>
      </div>
    </main>
  );
}
export default Login;