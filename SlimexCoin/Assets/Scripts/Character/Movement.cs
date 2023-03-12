using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using System;

public class Movement : MonoBehaviour
{
    public static event Action onPlayerDeath;

    public float MovementSpeed = 3;
    public float JumpForce = 5;
    private bool isJump;
    
    public float jumpStartTime = 0.3f;
    private float jumpTime;

    private bool isWin;

    private bool isDead;
    private int Point;

    bool facingRight = true;

    private Collider2D _spriteCollider;
    public Animator animator;
    private Rigidbody2D _rigidbody;

    void Start()
    {
        _rigidbody = GetComponent<Rigidbody2D>();
        Point = 0;
        isDead = false;
    }

    void Update()
    {

        if (!isDead)
        {
            //move
            var movement = Input.GetAxisRaw("Horizontal");
            if (movement < 0 && facingRight) Flip();
            if (movement > 0 && !facingRight) Flip();
            transform.position += new Vector3(movement, 0, 0) * Time.deltaTime * MovementSpeed;

            //jumb 
            Jump();
        }

        //set yVelocity 
        animator.SetFloat("yVelocity", _rigidbody.velocity.y);

        //check win
        if (isWin)
        {

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

        if (collision.gameObject.tag == "Enemy")
        {
            isWin = false;
            isDead = true;
            animator.SetBool("isDead", isDead);
            Destroy(_spriteCollider);
            onPlayerDeath?.Invoke();
        }

        if (collision.gameObject.tag == "Goal")
        {
            isWin = true;
        }

        if (collision.gameObject.tag == "Coin")
        {
            Point++;
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

    //Jump
    void Jump()
    {
        if (Input.GetKeyDown(KeyCode.UpArrow) && isJump == false)
        {
            jumpTime = jumpStartTime;
            _rigidbody.velocity = Vector2.up * JumpForce;
        }
        
        if(Input.GetKey(KeyCode.UpArrow) && isJump == true)
        {
            if(jumpTime> 0)
            {
                _rigidbody.velocity = Vector2.up * JumpForce;
                jumpTime -= Time.deltaTime;
            }
        }
    }
}
