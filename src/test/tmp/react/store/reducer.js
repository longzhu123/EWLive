import {fromJS} from 'immutable';
import * as ActionConstants from './constants';

const defaultState = fromJS(
    {
        #{humpName}List:[],
        selectIds:[],
        pageIndex:1,
        totalSize:0,
        queryObj:{},
        curOperRowObj:{},
        showAdd#{className}Modal:false,
        showView#{className}Modal:false,
        showUpdate#{className}Modal:false
    }
);

export default (state = defaultState, action) => {
    switch (action.type){
        case ActionConstants.LOAD_#{upName}_LIST:
            return state.set("#{humpName}List",action.#{humpName}List).set("totalSize",action.total).set("pageIndex",action.current);
        case ActionConstants.TABLE_SELECT_CHANGE:
            return state.set("selectIds",action.selectIds);
        case ActionConstants.TABLE_DEL_ITEM:
            return state.set("selectIds",action.selectIds).set("#{humpName}List",action.#{humpName}List).set("totalSize",action.total).set("pageIndex",1);
        case ActionConstants.CHANGE_FILTER_PARAM:
            return state.set("queryObj",action.queryObj);
        case ActionConstants.RESET_LOAD_GRID:
            return state.set("#{humpName}List",action.#{humpName}List).set("totalSize",action.total).set("pageIndex",action.current).set("queryObj",fromJS({}));
        case ActionConstants.IS_SHOW_ADD_#{upName}_MODAL:
            return state.set("showAdd#{className}Modal",action.isShow);
        case ActionConstants.IS_SHOW_VIEW_#{upName}_MODAL:
            return state.set("showView#{className}Modal",action.isShow);
        case ActionConstants.IS_SHOW_UPDATE_#{upName}_MODAL:
            return state.set("showUpdate#{className}Modal",action.isShow);
        case ActionConstants.ADD_#{upName}_OPER:
            return state.set("#{humpName}List",action.#{humpName}List).set("totalSize",action.total).set("pageIndex",action.current).set("queryObj",fromJS({})).set("showAdd#{className}Modal",action.showAdd#{className}Modal);
        case ActionConstants.UPDATE_#{upName}_OPER:
            return state.set("#{humpName}List",action.#{humpName}List).set("totalSize",action.total).set("pageIndex",action.current).set("queryObj",fromJS({})).set("showUpdate#{className}Modal",action.showAdd#{className}Modal);
        case ActionConstants.GET_DETAILBY_ID:
            let showUpdate#{className}Modal = false;
            let showView#{className}Modal = false;
            if(action.opera === "update"){
                showUpdate#{className}Modal = true;
            }else if(action.opera === "view"){
                showView#{className}Modal = true;
            }
            return state.set("curOperRowObj",action.curOperRowObj).set("showUpdate#{className}Modal",fromJS(showUpdate#{className}Modal)).set("showView#{className}Modal",fromJS(showView#{className}Modal));
        default:
            return state;
    }
}