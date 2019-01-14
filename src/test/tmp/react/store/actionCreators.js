import * as ActionConstants from './constants';
import {fromJS} from 'immutable';
import * as StringConstants from "../../../../../constant";
import util from '../../../../../util/util';
import {Modal} from 'antd';
//加载#{comment}ListAction
const load#{className}ListAction = (list, current, total) => ({
    type: ActionConstants.LOAD_#{upName}_LIST,
    #{humpName}List: fromJS(list),
    current,
    total
});

//加载#{comment}List
export const load#{className}List = (current, querParams) => {
    return (dispatch) => {
        querParams.current = current;
        querParams.size = StringConstants.PAGE_SIZE;
        let options = {
            url: StringConstants.SERVER_URL + "/#{className}/likeSearch#{className}ByPage",
            data: querParams
        };
        util.ajax(options).then((res => {
            dispatch(load#{className}ListAction(res.data.records, res.data.current, res.data.total));
        }));
    }
};


//表格复选框chage Action
const tableSelectChangeAction = (selectIds) => ({
    type: ActionConstants.TABLE_SELECT_CHANGE,
    selectIds: fromJS(selectIds)
});

//表格复选框chage
export const tableSelectChange = (selectedRowKeys) => {
    return (dispatch) => {
        dispatch(tableSelectChangeAction(selectedRowKeys));
    }
};

//删除表格项的数据 Action
const delItemAction = (#{humpName}List) => ({
    type: ActionConstants.TABLE_DEL_ITEM,
    selectIds: fromJS([]),
    #{humpName}List: fromJS(#{humpName}List.records),
    total: #{humpName}List.total
});

//删除表格项的数据
export const delItem = (selectIds, querParams) => {
    return (dispatch) => {
        let options = {
            url: StringConstants.SERVER_URL + "/#{className}/deleteBatch#{className}ByIds",
            data: {"ids": selectIds}
        };
        util.ajax(options).then((res => {
            Modal.success({
                "title": "信息提示",
                "content": "删除成功"
            });
            querParams.current = StringConstants.DEFAULT_PAGE_CURRENT;
            querParams.size = StringConstants.PAGE_SIZE;
            let #{humpName}Options = {
                url: StringConstants.SERVER_URL + "/#{className}/likeSearch#{className}ByPage",
                data: querParams
            };
            util.ajax(#{humpName}Options).then((res => {
                dispatch(delItemAction(res.data));
            }));

        }));

    }
};

//改变过滤表单参数
const changeFilterParamAction = (queryObj) => ({
    type: ActionConstants.CHANGE_FILTER_PARAM,
    queryObj: fromJS(queryObj)
});


//条件查询表格
export const filterForm = (queryObj) => {
    return (dispatch) => {

        dispatch(changeFilterParamAction(queryObj));
        queryObj.current = 1;
        queryObj.size = StringConstants.PAGE_SIZE;
        let options = {
            url: StringConstants.SERVER_URL + "/#{className}/likeSearch#{className}ByPage",
            data: queryObj
        };
        util.ajax(options).then((res => {
            dispatch(load#{className}ListAction(res.data.records, res.data.current, res.data.total));
        }));
    }
};


//重置表格ListAction
const resetLoadGridAction = (list, current, total) => ({
    type: ActionConstants.RESET_LOAD_GRID,
    #{humpName}List: fromJS(list),
    current,
    total
});

//重置表格
export const resetLoadGrid = (querParams) => {
    return (dispatch) => {
        querParams.current = 1;
        querParams.size = StringConstants.PAGE_SIZE;
        let options = {
            url: StringConstants.SERVER_URL + "/#{className}/likeSearch#{className}ByPage",
            data: querParams
        };
        util.ajax(options).then((res => {
            dispatch(resetLoadGridAction(res.data.records, res.data.current, res.data.total));
        }));
    }
};

//是否显示添加#{comment}模态框Action
const isShowAdd#{className}ModalAction = (isShow) => ({
    type: ActionConstants.IS_SHOW_ADD_#{upName}_MODAL,
    isShow
});

//是否显示添加#{comment}模态框
export const isShowAdd#{className}Modal = (isShow) => {
    return (dispatch) => {
        dispatch(isShowAdd#{className}ModalAction(isShow));
    }
};


//是否显示查看#{comment}模态框Action
const isShowView#{className}ModalAction = (isShow) => ({
    type: ActionConstants.IS_SHOW_VIEW_#{upName}_MODAL,
    isShow
});

//是否显示查看#{comment}模态框
export const isShowView#{className}Modal = (isShow) => {
    return (dispatch) => {
        dispatch(isShowView#{className}ModalAction(isShow));
    }
};


//是否显示修改#{comment}模态框Action
const isShowUpdate#{className}ModalAction = (isShow) => ({
    type: ActionConstants.IS_SHOW_UPDATE_#{upName}_MODAL,
    isShow
});

//是否显示修改#{comment}模态框
export const isShowUpdate#{className}Modal = (isShow) => {
    return (dispatch) => {
        dispatch(isShowUpdate#{className}ModalAction(isShow));
    }
};

//添加#{comment}Action
const add#{className}OperAction = (res) => ({
    type: ActionConstants.ADD_#{upName}_OPER,
    #{humpName}List: fromJS(res.data.records),
    current: res.data.current,
    total: res.data.total,
    showAdd#{className}Modal: false
});

//添加#{comment}
export const add#{className}Oper = (add#{className}Obj, querParams) => {
    return (dispatch) => {
        let options = {
            url: StringConstants.SERVER_URL + "/#{className}/add#{className}",
            data: add#{className}Obj
        };
        util.ajax(options).then((res => {
            Modal.success({
                "title": "信息提示",
                "content": "添加成功"
            });

            querParams.current = StringConstants.DEFAULT_PAGE_CURRENT;
            querParams.size = StringConstants.PAGE_SIZE;
            let #{humpName}Options = {
                url: StringConstants.SERVER_URL + "/#{className}/likeSearch#{className}ByPage",
                data: querParams
            };
            util.ajax(#{humpName}Options).then((res => {
                dispatch(add#{className}OperAction(res));
            }));
        }));
    }
};


//修改#{comment}Action
const update#{className}OperAction = (res) => ({
    type: ActionConstants.UPDATE_#{upName}_OPER,
    #{humpName}List: fromJS(res.data.records),
    current: res.data.current,
    total: res.data.total,
    showUpdate#{className}Modal: false
});

//修改#{comment}
export const updateItem = (updateObj, querParams) => {
    return (dispatch) => {
        let options = {
            url: StringConstants.SERVER_URL + "/#{className}/update#{className}ById",
            data: updateObj
        };
        util.ajax(options).then((res => {
            Modal.success({
                "title": "信息提示",
                "content": "修改成功"
            });

            querParams.current = StringConstants.DEFAULT_PAGE_CURRENT;
            querParams.size = StringConstants.PAGE_SIZE;
            let #{humpName}Options = {
                url: StringConstants.SERVER_URL + "/#{className}/likeSearch#{className}ByPage",
                data: querParams
            };
            util.ajax(#{humpName}Options).then((res => {
                dispatch(update#{className}OperAction(res));
            }))
        }));
    }
};

//根据id查询详情
export const getDetailById = (id, operate) => {
    return (dispatch) => {
        let options = {
            url: StringConstants.SERVER_URL + "/#{className}/get#{className}ById",
            data: {"id": id}
        };


        util.ajax(options).then((res => {
            let data = res.data;
            dispatch(getDetailByIdAction(data, operate));
        }));

    }
};

/**
 * 修改#{comment}Action
 * @param curOperRowObj  当前操作的表格行对象
 *
 */
const getDetailByIdAction = (curOperRowObj, opera) => ({
    type: ActionConstants.GET_DETAILBY_ID,
    curOperRowObj: fromJS(curOperRowObj),
    opera
});

