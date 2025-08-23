import axios from "axios";

export default class ApiService {

    static BASE_URL = "https://localhost:8040"

    static getHeader(){
        const token = localStorage.getItem("token");
        return {
            headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        };
    }

    /**AUTH */

    static async registerUser(registration) {
        const response = await axios.post(`${this.BASE_URL}/auth/register`, registration);
        return response.data;
    }

    static async loginUser(loginDetails) {
        const response = await axios.post(`${this.BASE_URL}/auth/login`, loginDetails);
        return response.data;

    }

    /***USERS */

    static async getAllUsers(){
        const response = await axios.get(`${this.BASE_URL}/users/all`, {
            headers: this.getHeader()
        });
        return response.data;
    }

    static async getUserProfile(){
        const response = await axios.get(`${this.BASE_URL}/users/get-logged-in-profile-info`, {
            headers: this.getHeader()
        });
        return response.data;
    }

    static async getUser(usersId){
        const response = await axios.get(`${this.BASE_URL}/users/get-by-id/${usersId}`, {
            headers: this.getHeader()
        });
        return response.data;
    }

    static async getUserBookings(userId){
        const response = await axios.get(`${this.BASE_URL}/users/get-user-bookings/${userId}`, {
            headers: this.getHeader()
        });
        return response.data;
    }

    static async deleteUser(userId){
        const response = await axios.delete(`${this.BASE_URL}/users/delete/${userId}`, {
            headers: this.getHeader()
        });
        return response.data;
    }

    /**ROOM */
    static async addRoom(formData){
        const result = await axios.post(`${this.BASE_URL}/rooms/add`, formData, {
            headers: {
                ...this.getHeader(),
                "Content-Type": "multipart/form-data"
            }
        });
        return result.data;
    }

    static async getAllAvaiableRooms(){
        const result = await axios.get(`${this.BASE_URL}/rooms/all-available-rooms`)
        return result.data;
    }

    static async getAvailableRoomsByDateAndType(checkInDate, checkOutDate, roomType) {
        const result = await axios.get(
            `${this.BASE_URL}/rooms/available-rooms-by-date-and-type?checkInDate=${checkInDate}
            &checkOutDate=${checkOutDate}&roomType=${roomType}`)
        return result.data;
    }

    static async getRoomTypes() {
        const response = await axios.get(`${this.BASE_URL}/rooms/types`)
        return response.data
    }

    static async getAllRooms() {
        const result = await axios.get(`${this.BASE_URL}/rooms/all`)
        return result.data
    }

    static async getRoomById(roomId) {
        const result = await axios.get(`${this.BASE_URL}/rooms/room-by-id/${roomId}`)
        return result.data
    }

    static async deleteRoom(roomId) {
        const result = await axios.delete(`${this.BASE_URL}/rooms/delete/${roomId}`, {
            headers: this.getHeader()
        });
        return result.data;
    }

    static async updateRoom(roomId, formData) {
        const result = await axios.put(`${this.BASE_URL}/rooms/update/${roomId}`, formData, {
            headers: {
                ...this.getHeader(),
                'Content-Type': 'multipart/form-data'
            }
        });
        return result.data;
    }

    /**BOOKING */
    static async bookRoom(roomId, userId,booking){

        console.log("USER ID IS: " + userId)

        const response = await axios.post(`${this.BASE_URL}/bookings/book-room/${roomId}/${userId}`, {
            headers: this.getHeader()
        });
        return response.data;
    }

    static async getAllBookings() {
        const result = await axios.get(`${this.BASE_URL}/bookings/all`, {
            headers: this.getHeader()
        })
        return result.data
    }

    static async getBookingByConfirmationCode(bookingCode) {
        const result = await axios.get(`${this.BASE_URL}/bookings/get-by-confirmation-code/${bookingCode}`)
        return result.data
    }

    static async cancelBooking(bookingId) {
        const result = await axios.delete(`${this.BASE_URL}/bookings/cancel/${bookingId}`, {
            headers: this.getHeader()
        })
        return result.data
    }


    /** AUTHENTICATION */
    static logout(){
        localStorage.removeItem("token");
        localStorage.removeItem("role");
    }

    static isAuthenticated() {
        return localStorage.getItem("token") !== null;
    }

    static isAdmin() {
        return localStorage.getItem("role") === "ADMIN";
    }

    static isUser() {
        return localStorage.getItem("role") === "USER";
    }

}