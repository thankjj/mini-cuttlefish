import axios from 'axios';
import { req } from './axiosFun';

var address = "http://119.29.174.105:8888";
// var address = "http://localhost:8888";

/**
 * 内容管理 
 **/
// 内容管理-获取内容列表
export const contentList = (params) => { return req("post", address + "/cuttlefish/content/getContentByPage", params) };

// 内容管理-内容有效状态更改
export const changeValidStatus = (params) => { return req("post", address + "/cuttlefish/content/changeValidStatus", params) };

// 内容管理-内容保存/修改
export const saveOrUpdateContent = (params) => { return req("post", address + "/cuttlefish/content/saveOrUpdateContent", params) };

// 内容管理-内容保存/修改
export const checkContent = (params) => { return req("post", address + "/cuttlefish/content/checkContent", params) };

// 内容管理-批量导入
export const resolveUploadFile = (params) => { return req("post", address + "/cuttlefish/content/resolveUploadFile", params) };




// 获取所有分类信息
export const contentCategoryList = () => { return req("post", address + "/cuttlefish/common/getContentCategory") };