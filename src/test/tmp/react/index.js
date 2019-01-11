import React, {PureComponent} from 'react';
import {connect} from 'react-redux';
import {Button, Card, Icon, Table,Modal} from 'antd';
import SearchForm from '../../../../common/Form/SearchForm';
import ViewForm from '../../../../common/Form/ViewForm';
import EditForm from '../../../../common/Form/EditForm';
import * as StringConstants from '../../../../constant';
import './index.css';
import {actionCreators} from "./store";

const confirm = Modal.confirm;
let querParams = {};
let toCurOperRowObj = {};

//#{comment}管理组件
class #{className} extends PureComponent {

    componentDidMount() {
        this.props.load#{className}List();
    }

    onRef = (ref) => {
        this.add#{className}FormRef = ref;
        this.update#{className}FormRef = ref;
    };

    render() {
        const {#{humpName}List, tableSelectChange, delItem, selectIds, onShowSizeChange, pageIndex, totalSize, showViewModal, showUpdateModal, filterForm, queryObj, resetLoadGrid, isShowAdd#{className}Modal, showAdd#{className}Modal, showView#{className}Modal, showUpdate#{className}Modal, isShowView#{className}Modal, isShowUpdate#{className}Modal, curOperRowObj} = this.props;
        querParams = queryObj.toJS();
        toCurOperRowObj = curOperRowObj.toJS();
        const #{humpName}DataList = #{humpName}List.toJS();
        const selectDataIds = selectIds.toJS();
        const rowSelection = {
            onChange: tableSelectChange
        };
        const columns = [
            {
                title: '邮箱',
                dataIndex: 'email',
                align: "center"
            }, {
                title: '昵称',
                dataIndex: 'nickName',
                align: "center"
            }, {
                title: '优币',
                dataIndex: 'ewCoin',
                align: "center"
            }
            , {
                title: '操作',
                key: 'control',
                align: "center",
                render: (text, record) => (
                    <span className='control-container'>
                         <Button type="primary" ghost onClick={() => showViewModal(record.id)}>查看</Button>
                         <Button type="primary" ghost onClick={() => showUpdateModal(record.id)}>修改</Button>
                    </span>
                ),
            }
        ];
        //搜索表单组件的配置参数
        const searchFormOptions = [
            {
                type: "input",
                lable: "邮箱",
                placeholder: "邮箱",
                width: "200px",
                field: "email"
            },
            {
                type: "input",
                lable: "昵称",
                placeholder: "昵称",
                width: "200px",
                field: "nickName"
            }
        ];

        //详细的配置参数
        const viewOptions = [
            {
                type: "text",
                lable: "邮箱",
                field: "email"
            },
            {
                type: "text",
                lable: "昵称",
                field: "nickName"
            },
            {
                type: "text",
                lable: "优币",
                field: "ewCoin"
            }
        ];


        //添加表单的配置参数
        const addFormOptions = [
            {
                type: "input",
                lable: "邮箱",
                placeholder: "邮箱",
                width: "200px",
                field: "email",
                validate: [
                    {
                        type: 'email', message: '邮箱格式不正确!',
                    },
                    {
                        required: true, message: '请输入邮箱!',
                    }
                ]
            },
            {
                type: "input",
                lable: "密码",
                placeholder: "密码",
                width: "200px",
                field: "password",
                validate: [
                    {required: true, message: '请输入密码!'}
                ]
            },
            {
                type: "input",
                lable: "昵称",
                placeholder: "昵称",
                width: "200px",
                field: "nickName",
                validate: [
                    {required: true, message: '请输入昵称!'}
                ]
            },
            {
                type: "input",
                lable: "优币",
                placeholder: "优币",
                width: "200px",
                field: "ewCoin",
                validate: [
                    {
                        pattern: new RegExp(/^[1-9]\d*$/, "g"),
                        message: '只能输入数值格式'
                    }, {
                        required: true, message: '请输入优币!',
                    }
                ]
            }
        ];
        //编辑表单的配置参数
        const updateFormOptions = [
            {
                type: "input",
                lable: "邮箱",
                placeholder: "邮箱",
                width: "200px",
                field: "email",
                initialValue: toCurOperRowObj.email,
                validate: [
                    {
                        type: 'email', message: '邮箱格式不正确!',
                    },
                    {
                        required: true, message: '请输入邮箱!',
                    }
                ]
            },
            {
                type: "input",
                lable: "密码",
                placeholder: "密码",
                width: "200px",
                field: "password",
                initialValue: toCurOperRowObj.password,
                validate: [
                    {required: true, message: '请输入密码!'}
                ]
            },
            {
                type: "input",
                lable: "昵称",
                placeholder: "昵称",
                width: "200px",
                field: "nickName",
                initialValue: toCurOperRowObj.nickName,
                validate: [
                    {required: true, message: '请输入昵称!'}
                ]
            },
            {
                type: "input",
                lable: "优币",
                placeholder: "优币",
                width: "200px",
                field: "ewCoin",
                initialValue: toCurOperRowObj.ewCoin,
                validate: [
                    {
                        pattern: new RegExp(/^[1-9]\d*$/, "g"),
                        message: '只能输入数值格式'
                    }, {
                        required: true, message: '请输入优币!',
                    }
                ]
            }
        ];


        return (
            <div>
                <Card>
                    {/*将父组件的filterForm方法传给子组件filterForm*/}
                    <SearchForm searchOptions={searchFormOptions} resetLoadGrid={resetLoadGrid}
                                filterForm={filterForm}/>
                </Card>
                <Card>
                    <div>
                        <Button type="primary" icon="plus" style={{marginRight: '10px'}}
                                onClick={() => isShowAdd#{className}Modal(true)}>添加</Button>
                        <button className="ant-btn delBtn" onClick={() => delItem(selectDataIds, querParams)}><Icon
                            type="delete"/>删除
                        </button>
                    </div>
                    <br/>
                    <Table
                        rowSelection={rowSelection}
                        rowKey="id"
                        bordered
                        columns={columns}
                        dataSource={#{humpName}DataList}
                        size="small"
                        pagination={{
                            showQuickJumper: true,
                            current: pageIndex,
                            total: totalSize,
                            onChange: onShowSizeChange,
                            showTotal: () => {
                                return `共 ${totalSize} 条`
                            },
                        }}
                    />
                </Card>

                <div>
                    <Modal
                        title="添加#{comment}"
                        visible={showAdd#{className}Modal}
                        onOk={() => {
                            this.add#{className}FormRef.addFormValidate(querParams)
                        }}
                        onCancel={() => isShowAdd#{className}Modal(false)}
                        destroyOnClose
                    >
                        <EditForm editFormOption={addFormOptions} editAction={this.props.add#{className}Oper}
                                  onRef={this.onRef}/>
                    </Modal>
                </div>


                <div>
                    <Modal
                        title="查看#{comment}"
                        visible={showView#{className}Modal}
                        onCancel={() => isShowView#{className}Modal(false)}
                        destroyOnClose
                    >
                        <ViewForm viewOptions={viewOptions} viewData={toCurOperRowObj}/>
                    </Modal>
                </div>

                <div>
                    <Modal
                        title="修改#{comment}"
                        visible={showUpdate#{className}Modal}
                        onOk={() => this.update#{className}FormRef.updateFormValidate(toCurOperRowObj.id, querParams)}
                        onCancel={() => isShowUpdate#{className}Modal(false)}
                        destroyOnClose
                    >
                        <EditForm editFormOption={updateFormOptions} editAction={this.props.updateItem}
                                  onRef={this.onRef}/>
                    </Modal>
                </div>
            </div>
        )

    }

}


const mapState = (state) => ({
    #{humpName}List: state.get("#{humpName}SettingReducer").get("#{humpName}List"),
    selectIds: state.get("#{humpName}SettingReducer").get("selectIds"),
    pageIndex: state.get("#{humpName}SettingReducer").get("pageIndex"),
    totalSize: state.get("#{humpName}SettingReducer").get("totalSize"),
    queryObj: state.get("#{humpName}SettingReducer").get("queryObj"),
    showAdd#{className}Modal: state.get("#{humpName}SettingReducer").get("showAdd#{className}Modal"),
    showView#{className}Modal: state.get("#{humpName}SettingReducer").get("showView#{className}Modal"),
    showUpdate#{className}Modal: state.get("#{humpName}SettingReducer").get("showUpdate#{className}Modal"),
    curOperRowObj: state.get("#{humpName}SettingReducer").get("curOperRowObj")
});

const mapDispatchToProps = (dispatch) => ({
    //加载#{comment}列表
    load#{className}List() {
        dispatch(actionCreators.load#{className}List(StringConstants.DEFAULT_PAGE_CURRENT, {}));
    },
    //表格复选框change事件
    tableSelectChange(selectedRowKeys) {
        dispatch(actionCreators.tableSelectChange(selectedRowKeys));
    },
    //删除项事件
    delItem(selectDataIds, querParams) {
        confirm({
            title: '确认删除当前数据吗?',
            okText: '确定',
            okType: 'danger',
            cancelText: '取消',
            onOk() {
                if (selectDataIds.length === 0) {
                    Modal.error({
                        "title": "错误提示",
                        "content": "请选择要删除的记录"
                    });
                    return;
                }
                dispatch(actionCreators.delItem(selectDataIds, querParams));
            }
        });

    },
    //表格分页change事件
    onShowSizeChange(current) {
        dispatch(actionCreators.load#{className}List(current, querParams));
    },
    //查看单条记录的详情
    showViewModal(id) {
        dispatch(actionCreators.getDetailById(id, "view"));
    },
    //显示修改模态框
    showUpdateModal(id) {
        dispatch(actionCreators.getDetailById(id, "update"));
    },
    //修改操作
    updateItem(updateObj, queryObj) {
        dispatch(actionCreators.updateItem(updateObj, queryObj));
    },
    //条件查询表格
    filterForm(queryObj) {
        dispatch(actionCreators.filterForm(queryObj));
    },
    //重置表格
    resetLoadGrid() {
        dispatch(actionCreators.resetLoadGrid({}));
    },
    //是否显示添加#{comment}模态框
    isShowAdd#{className}Modal(isShow) {
        dispatch(actionCreators.isShowAdd#{className}Modal(isShow));
    },
    //添加#{comment}
    add#{className}Oper(add#{className}Obj, querParam) {
        dispatch(actionCreators.add#{className}Oper(add#{className}Obj, querParam));
    },
    //是否显示查看#{comment}模态框
    isShowView#{className}Modal(isShow) {
        dispatch(actionCreators.isShowView#{className}Modal(isShow));
    },
    //是否显示修改#{comment}模态框
    isShowUpdate#{className}Modal(isShow) {
        dispatch(actionCreators.isShowUpdate#{className}Modal(isShow));
    }
});

export default connect(mapState, mapDispatchToProps)(#{className});