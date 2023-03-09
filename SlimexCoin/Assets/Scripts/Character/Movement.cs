using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;

public class Movement : MonoBehaviour
{
    public float MovementSpeed = 5;

    public float JumpForce = 5;

    public bool isJump;

    public bool isHurt;

    public bool isDead;

    public Animator animator;

    private Rigidbody2D _rigidbody;

    bool facingRight = true;

    void Start()

    {
        _rigidbody = GetComponent<Rigidbody2D>();
    }

    void Update()

    {
        animator.SetBool("isHurt", true);
        //move
        var movement = Input.GetAxisRaw("Horizontal");
        if (movement < 0 && facingRight) Flip();
        if(movement > 0 && !facingRight) Flip();
        transform.position += new Vector3(movement, 0, 0) * Time.deltaTime * MovementSpeed;

        //jumb
        if (Input.GetButtonDown("Jump") && isJump == false)
        { 
            _rigidbody.AddForce(new Vector2(0, JumpForce), ForceMode2D.Impulse);
        }

        //Dead
        if (Input.GetKey(KeyCode.Z) && Mathf.Abs(movement) == 0)
        {
            isDead = true;
            animator.SetBool("isDead", isDead);
        }
        else
        {
            isDead = false;
            animator.SetBool("isDead", isDead);
        }

        //Hurt
        if (Input.GetKey(KeyCode.X) && Mathf.Abs(movement) == 0)
        {
            isHurt = true;
            animator.SetBool("isHurt", isHurt);
        }
        else
        {
            isHurt = false;
            animator.SetBool("isHurt", isHurt);
        }
    }


    //check landing
    void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.gameObject.tag == "Ground")
        {
            isJump = false;
            animator.SetBool("isJump", isJump);
        }
    }

    void OnTriggerExit2D(Collider2D collision)
    {
        if (collision.gameObject.tag == "Ground")
        {
            isJump = true;
            animator.SetBool("isJump", isJump);
        }
    }


    //flip
    void Flip()
    {
        Vector3 currentScale = gameObject.transform.localScale;
        currentScale.x *= -1;
        gameObject.transform.localScale = currentScale;
        facingRight = !facingRight;
    }

}
