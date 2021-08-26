package com.example.commonlib.view

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.customview.widget.ViewDragHelper
import kotlin.math.max
import kotlin.math.min

class MoveableLayout(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {
	private val initPointPosition = Point()
	
	private val cb = object : ViewDragHelper.Callback() {
		//返回true才能滑动
		override fun tryCaptureView(child: View, pointerId: Int): Boolean {
			return getChildAt(childCount - 1) === child
		}
		
		//这个地方实际上left就代表 你将要移动到的位置的坐标。返回值就是最终确定的移动的位置。
		// 我们要让view滑动的范围在我们的layout之内
		//实际上就是判断如果这个坐标在layout之内 那我们就返回这个坐标值。
		//如果这个坐标在layout的边界处 那我们就只能返回边界的坐标给他。不能让他超出这个范围
		//除此之外就是如果你的layout设置了padding的话，也可以让子view的活动范围在padding之内的.
		override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
			//取得左边界的坐标
			val leftBound = paddingLeft
			//取得右边界的坐标
			val rightBound = width - child.width - leftBound
			//这个地方的含义就是 如果left的值 在leftbound和rightBound之间 那么就返回left
			//如果left的值 比 leftbound还要小 那么就说明 超过了左边界 那我们只能返回给他左边界的值
			//如果left的值 比rightbound还要大 那么就说明 超过了右边界，那我们只能返回给他右边界的值
			return min(max(left, leftBound), rightBound)
		}
		
		override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
			//取得上边界的坐标
			val topBound = paddingTop
			//取得下边界的坐标
			val bottomBound = height - child.height - topBound
			return min(max(top, topBound), bottomBound)
		}
		
		override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
			//松手的时候 判断如果是这个view 就让他回到起始位置
			if (getChildAt(childCount - 1) === releasedChild) {
				//这边代码你跟进去去看会发终调用的是startScroll这个方法 所以我们就明白还要在computeScroll方法里刷新现最
				viewDragHelper.settleCapturedViewAt(initPointPosition.x, initPointPosition.y)
				invalidate()
			}
		}
		
		/**
		 * 重写以下2个方法，并在可拖拽并点击的view上加上clickable=true，就能实现既能拖动又能点击
		 */
		override fun getViewHorizontalDragRange(child: View): Int {
			return measuredWidth - child.measuredWidth
		}
		
		override fun getViewVerticalDragRange(child: View): Int {
			return measuredHeight - child.measuredHeight
		}
	}
	
	override fun computeScroll() {
		if (viewDragHelper.continueSettling(true))
			invalidate()
	}
	
	private var viewDragHelper: ViewDragHelper = ViewDragHelper.create(this, cb)
	
	
	override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
		return viewDragHelper.shouldInterceptTouchEvent(ev!!)
	}
	
	override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
		super.onLayout(changed, left, top, right, bottom)
		initPointPosition.x = getChildAt(childCount - 1).left
		initPointPosition.y = getChildAt(childCount - 1).top
	}
	
	override fun onTouchEvent(event: MotionEvent?): Boolean {
		viewDragHelper.processTouchEvent(event!!)
		return true
	}
}