/**
 * TrinaryTree object.
 * @author Ginny Huang
 */
public class TrinaryTree {
	
	Node root;
	
	TrinaryTree() {
		root = null;
	}
	
	/**
	 * This method tries to add a node to the Trinary Tree given
	 * the specific value to insert
	 *
	 * @param  value  the value of the node to be inserted
	 */
	void insert(int value) {
		if (root == null) {
			root = new Node(value);
			return;
		}
		Node pointer = root;
		Node toInsert = new Node(value);
		// Finde the right parent node and insert into a null space
		while (pointer != null) {
			int cur = pointer.val;
			if (value < cur) {
				if (pointer.left == null) {
					pointer.left = toInsert;
					return;
				}
				pointer = pointer.left;
			} else if (value > cur) {
				if (pointer.right == null) {
					pointer.right = toInsert;
					return;
				}
				pointer = pointer.right;
			} else { 
				// When the value is a duplicate, just "tuck" it
				// under the first occurence
				toInsert.middle = pointer.middle;
				pointer.middle = toInsert;
				return;
			}
		}
	}
	
	/**
	 * This method tries to delete a node from the Trinary Tree given
	 * the specific value
	 *
	 * @param  value  the value of the node to be deleted
	 * @return      whether a node is deleted; if the Tree does not contain 
	 *              the specific value, the method returns false.
	 */
	boolean delete(int value) {
		Node parent = new Node(Integer.MIN_VALUE);
		Node toDelete = root;
		parent.right = toDelete;
		while (toDelete != null) {
			if (value < toDelete.val) {
				parent = toDelete;
				toDelete = toDelete.left;
			} else if (value > toDelete.val) {
				parent = toDelete;
				toDelete = toDelete.right;
			} else {
				break;
			}
		}
		if (toDelete == null) {
			return false;
		}
		if (toDelete.middle != null) {
			toDelete.middle = toDelete.middle.middle;
		} else if (toDelete.left == null) {
			if (toDelete == parent.left) {
				parent.left = toDelete.right;
			} else {
				parent.right = toDelete.right;
			}
		} else {
			Node replacement = toDelete.left;
			Node parent_replacement = null;
			
			while (replacement.right != null) {
				parent_replacement = replacement;
				replacement = replacement.right;
			}
			// When the target node would NOT be replaced by its left child
			if (parent_replacement != null) { 
				parent_replacement.right = replacement.left;
				replacement.left = toDelete.left;
			}
			replacement.right = toDelete.right;
			if (toDelete == parent.left) {
				parent.left = replacement;
			} else {
				parent.right = replacement;
			}
			if (toDelete == root) {
				root = replacement;
			}
		}
		return true;
	}
	
}

/**
 * Node object
 */
class Node {
	int val;
	Node left;
	Node middle;
	Node right;
	
	Node(int value) {
		val = value;
		left = null;
		middle = null;
		right = null;
	}
}
