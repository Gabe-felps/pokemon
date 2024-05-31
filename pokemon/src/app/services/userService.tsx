import axios from "axios";

const userService = axios.create({
    baseURL: 'http://localhost:8080/users'
});

export default userService;