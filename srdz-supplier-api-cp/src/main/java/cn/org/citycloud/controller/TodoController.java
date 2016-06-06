//package cn.org.citycloud.controller;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import cn.org.citycloud.bean.TodoBean;
//import cn.org.citycloud.entity.Todo;
//import cn.org.citycloud.repository.TodoDao;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//
///**
// * 代办事宜接口
// * 
// * @author lanbo
// *
// */
//	/**
//	 * 获取单个待办事宜内容
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
//	@ApiOperation(value = "获取单个待办事宜内容", notes = "获取单个待办事宜内容", response = Todo.class)
//	public Object getTodo(@ApiParam(value = "待办事宜Id", required = true) @PathVariable int id) {
//		return todoDao.findOne(id);
//	}
//
//	/**
//	 * 添加代办事宜
//	 * 
//	 * @param todo
//	 */
//	@RequestMapping(value = "/todos", method = RequestMethod.POST)
//	@ApiOperation(value = "添加代办事宜", notes = "添加代办事宜")
//	public void addTodo(@ApiParam(value = "代办事宜对象", required = true) @Valid @RequestBody TodoBean todo) {
//
//		Todo entity = new Todo();
//		entity.setTodo(todo.getTodo());
//
//		todoDao.save(entity);
//
//	}
//
//	/**
//	 * 修改待办事宜
//	 * 
//	 * @param id
//	 * @param todo
//	 */
//	@RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
//	@ApiOperation(value = "修改待办事宜", notes = "修改待办事宜", httpMethod = "PUT")
//	public void editTodo(@ApiParam(value = "待办事宜Id", required = true) @PathVariable int id, @ApiParam(value = "代办事宜对象", required = true) @Valid @RequestBody TodoBean todo) {
//
//		Todo entity = todoDao.findOne(id);
//
//		if (entity != null) {
//			entity.setTodo(todo.getTodo());
//
//			todoDao.save(entity);
//		}
//
//	}
//
//	/**
//	 * 删除待办事宜
//	 * 
//	 * @param id
//	 */
//	@RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
//	@ApiOperation(value = "删除待办事宜", notes = "删除待办事宜", httpMethod = "DELETE")
//	public void deleteTodo(@ApiParam(value = "待办事宜Id", required = true) @PathVariable int id) {
//		todoDao.delete(id);
//	}
//
//}
