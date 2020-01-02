import axios from 'axios';
import { req } from './axiosFun';

var address = "http://119.29.174.105:8888";
// var address = "http://localhost:8888";

/**
 * 用户管理 
 **/
// 用户管理-获取用户列表
export const userList = (params) => { return req("post", address + "/cuttlefish/user/getUserByPage", params) };   

export const changeUserStatus = (params) => { return req("post", address + "/cuttlefish/user/changeUserStatus", params) };

// 用户反馈管理列表
export const feedbackList = (params) => { return req("post", address + "/cuttlefish/user/getFeedbackByPage", params) };

export const updateFeedbackStatus = (params) => { return req("post", address + "/cuttlefish/user/updateFeedbackStatus", params) };